package com.xiaoai.h5doc

import com.alibaba.fastjson.JSON
import com.sun.javadoc.ClassDoc
import com.sun.javadoc.MethodDoc
import com.sun.javadoc.RootDoc
import com.sun.javadoc.Type
import com.sun.tools.javadoc.Main
import com.xiaoai.h5doc.ApiDocBean
import java.io.BufferedWriter
import java.io.File
import java.io.FileWriter
import java.io.IOException
import java.lang.reflect.InvocationTargetException

/**
 * jsbridge api doc 生成器
 */
object ApiDocGenerator {

    fun start(modules: MutableList<String>) {
        val args: MutableList<String> = ArrayList()
        args.add("-doclet")
        args.add(ApiDocGenerator::class.java.name)
        args.addAll(modules)
        val docArgs = args.toTypedArray()
        Main.execute(*docArgs)
    }

    /**
     * javadoc 入口
     */
    @JvmStatic
    fun start(root: RootDoc): Boolean {
        val output = getJsBridgeData(root.classes())
        createJsBridgeJsonFile(JSON.toJSONString(output), "apidoc.json")
        createJsBridgeJsonFile(json2Markdown(output), "apidoc.md")
        return true
    }

    private fun getAllInterfaceTypes(clazz: ClassDoc): List<Type> {
        val showList = mutableListOf<Type>()
        var classDoc = clazz
        var hasSupClass = true
        while (hasSupClass) {
            showList.addAll(classDoc.interfaceTypes().toList())
            if (classDoc.superclass() == null || classDoc.superclass().name().equals("Object")) {
                hasSupClass = false
            } else {
                classDoc = clazz.superclass()
            }
        }
        return showList
    }

    private fun getJsBridgeData(classes: Array<ClassDoc>): ApiDocBean {
        println("--------------------- getJsBridgeData -----------------------")
        val output = ApiDocBean()
        val modules: MutableList<ApiDocBean.ModuleBean> = ArrayList()
        for (moduleDoc in classes) {

            var hasImplJsModule = false
            for (type in getAllInterfaceTypes(moduleDoc)) {
                if (type.simpleTypeName() == "IAcceptJsModule") {
                    hasImplJsModule = true
                }
            }
            if (!hasImplJsModule) {
                continue
            }

            println("--------------------- module: ${moduleDoc.name()} -----------------------")

            val module = ApiDocBean.ModuleBean()
            try {
                module.className = moduleDoc.name()
                module.moduleTitle = getTagSingle(moduleDoc, "moduleTitle")
                module.moduleName = getTagSingle(moduleDoc, "moduleName")
                module.description = getTagSingle(moduleDoc, "description")

                val moduleClass = Class.forName(moduleDoc.qualifiedName())

                println("* ${module.moduleName}")

                println("* ${module.moduleName}")

                val exs: MutableList<String> = ArrayList()
                var temp: Class<*>? = moduleClass
                while (temp != null && temp != Any::class.java) {
                    exs.add(temp.simpleName)
                    temp = temp.superclass
                }
                module.extendsX = exs

            } catch (e: ClassNotFoundException) {
                System.err.println(e.toString())
            } catch (e: NoSuchMethodException) {
                System.err.println(e.toString())
            } catch (e: IllegalAccessException) {
                System.err.println(e.toString())
            } catch (e: InstantiationException) {
                System.err.println(e.toString())
            } catch (e: InvocationTargetException) {
                System.err.println(e.toString())
            } catch (e: NoClassDefFoundError) {
                System.err.println(e.toString())
            } catch (e: ExceptionInInitializerError) {
                System.err.println(e.toString())
            }

            handlerBridges(moduleDoc, module)

            modules.add(module)
            println()
        }
        output.modules = modules
        return output
    }


    private fun handlerBridges(moduleDoc: ClassDoc, modules: ApiDocBean.ModuleBean) {
        val methods = moduleDoc.methods()
        val bridgesList: MutableList<ApiDocBean.ModuleBean.BridgeBean> = ArrayList()

        MethodsFor@ for (method in methods) {
            val bridgesMap = ApiDocBean.ModuleBean.BridgeBean()
            bridgesMap.bridgeName = method.name()
            bridgesMap.description = method.commentText()

            var hasJsAcceptBridge = false
            for (annotation in method.annotations()) {

                if (annotation.annotationType().name() != "JsAcceptBridge") {
                    continue
                }
                hasJsAcceptBridge = true

                for (elementValuePair in annotation.elementValues()) {
                    when {
                        elementValuePair.element().name() == "supportVersion" -> {
                            bridgesMap.supportVersion = elementValuePair.value().toString().toInt()
                        }
                        elementValuePair.element().name() == "deprecatedVersion" -> {
                            bridgesMap.deprecatedVersion =
                                elementValuePair.value().toString().toInt()
                        }
                        elementValuePair.element().name() == "isOnlyOnHtmlInitCall" -> {
                            bridgesMap.isOnlyOnHtmlInitCall =
                                elementValuePair.value().toString().toBoolean()
                        }
                        else -> {
                            System.err.println("有新的注解类型，请及时更新：" + elementValuePair.element().name())
                        }
                    }
                }
            }

            println(hasJsAcceptBridge)

            if (!hasJsAcceptBridge) {
                continue
            }

            println("- bridge: " + method.name())

            if (method.returnType().typeName() != Void.TYPE.name) {
                bridgesMap.sync = true
            } else {
                val sync = getTagSingle(method, "sync")
                if (sync == null) {
                    System.err.println("警告： bridge sync exception :" + method.name())
                } else {
                    bridgesMap.sync = sync.toBoolean()
                }
            }

            bridgesMap.see = getTagList(method, "see")
            bridgesMap.example = getTagList(method, "example")
            bridgesMap.callbackFunction = getTagList(method, "callbackFunction")
            bridgesMap.update = getTagList(method, "update")
            bridgesMap.inputData = getTagSingle(method, "inputData")
            bridgesMap.author = getTagSingle(method, "author")
            bridgesMap.callbackResult = getTagSingle(method, "result")
            bridgesMap.callbackCycle = getTagSingle(method, "cycle")
            bridgesMap.callbackError = getTagSingle(method, "error")
            bridgesList.add(bridgesMap)
        }
        if (bridgesList.size > 0) {
            modules.bridges = bridgesList
        }
    }

    private fun getTagList(methodDoc: MethodDoc, key: String): MutableList<String> {
        val tags = methodDoc.tags(key)
        if (tags.isEmpty()) {
            return ArrayList()
        }
        val list: MutableList<String> = ArrayList()
        for (tag in tags) {
            list.add(tag.text())
        }
        return list;
    }

    private fun getTagSingle(classDoc: ClassDoc, key: String): String? {
        val tags = classDoc.tags(key)
        if (tags.isEmpty()) {
            return null
        }
        val tag = tags[0]
        if (tags.size > 1) {
            System.err.println("tag数量异常: tag: $key size: $tags.size")
            return null
        }
        return tag.text()
    }

    private fun getTagSingle(methodDoc: MethodDoc, key: String): String? {
        val tags = methodDoc.tags(key)
        if (tags.isEmpty()) {
            return null
        }
        val tag = tags[0]
        if (tags.size > 1) {
            System.err.println("tag数量异常: tag: $key size: $tags.size")
            return null
        }
        return tag.text()
    }

    private fun createJsBridgeJsonFile(output: String, exportName: String) {
        val exportPath = System.getProperty("user.dir") + "/build/$exportName"
        try {
            val file = File(exportPath)
            if (!file.exists() && !file.delete() && !file.createNewFile()) {
                System.err.println("show error")
                return
            }
            val fileWriter = FileWriter(file.absoluteFile)
            val bw = BufferedWriter(fileWriter)
            bw.write(output)
            bw.close()
            println("文档生成成功，路径: \n$exportPath")
        } catch (e: IOException) {
            e.printStackTrace()
        }
    }

    private fun json2Markdown(output: ApiDocBean): String {
        val result = StringBuilder()

        for (module in output.modules) {
            println("module ${module.className}")
            result.append("--------------------").appendln().appendln()
            result.append("# ${module.moduleTitle} ")
                .appendln()
                .appendln()
                .append(module.description)
                .appendln()
                .appendln()
                .append("* 接口模块：${module.moduleName}\n")
                .append("* Java类名：${module.className}\n")

            if (module.extendsX != null) {
                result.append("* 继承关系：")
                for (extendsX in module.extendsX) {
                    result.append(extendsX).append(" -> ")
                }
                result.append("Object")
                result.appendln().appendln()
            }

            result.appendln().append("<br>").appendln().appendln()
            result.append("### 参数说明").appendln().appendln()
            result.append("### 接口目录：\n\n<!-- toc -->\n\n")
            if (module.bridges == null) {
                continue
            }
            for (bridge in module.bridges) {
                result.append("### ${bridge.bridgeName}\n\n")
                result.append("${bridge.description}\n\n")

                if (bridge.deprecatedVersion != 0) {
                    result.append("> [!Warning|label: 已于：${bridge.deprecatedVersion} 版本过时 ]\n")
                    result.append("> ${bridge.deprecated}\n")
                }

                result.append("* 支持版本： `${bridge.supportVersion}`\n")
                if (bridge.update.isNotEmpty()) {
                    result.append("* 更新记录：\n")
                    for (s in bridge.update) {
                        result.append("\t- $s\n")
                    }
                }
                if (bridge.sync) {
                    result.append("* 同步调用： `支持`\n")
                } else {
                    result.append("* 同步调用： `不支持`\n")
                }
                if (bridge.see.isNotEmpty()) {
                    result.append("* 相关链接：\n")
                    for (s in bridge.see) {
                        result.append("\t- $s")
                    }
                    result.appendln()
                }
                result.append("* 输入参数： `")
                if (bridge.inputData == null || bridge.inputData.isEmpty()) {
                    result.append("无`\n")
                } else {
                    result.append(bridge.inputData + "`\n")
                }
                result.append("* result： `")
                if (bridge.callbackResult == null || bridge.callbackResult.isEmpty()) {
                    result.append("无`\n")
                } else {
                    result.append(bridge.callbackResult + "`\n")
                }
                result.append("* cycle： `")
                if (bridge.callbackCycle == null || bridge.callbackCycle.isEmpty()) {
                    result.append("无`\n")
                } else {
                    result.append(bridge.callbackCycle + "`\n")
                }
                result.append("* error： `")
                if (bridge.callbackError == null || bridge.callbackError.isEmpty()) {
                    result.append("无`\n")
                } else {
                    result.append(bridge.callbackError + "`\n")
                }
                if (bridge.example.isNotEmpty()) {
                    result.append("* example：\n\n")
                    result.append("``` javascript\n")
                    result.append(bridge.example)
                    result.append("\n```\n")
                }
                result.appendln()
            }
        }
        return result.toString()

    }
}