package com.xiaoai.h5doc;


import org.gradle.internal.impldep.com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * by ApiDoc json
 */
public class ApiDocBean {

    private List<ModuleBean> modules;

    public List<ModuleBean> getModules() {
        return modules;
    }

    public void setModules(List<ModuleBean> modules) {
        this.modules = modules;
    }

    public static class ModuleBean {
        /**
         * bridges : [{"author":"author","bridgeName":"getMiotDeviceIntention","callbackCycle":"null","callbackError":"null","callbackFunction":["functionName data:(json)","functionName2 data:(json)","functionName3 data:(json)"],"callbackResult":"JsonString: {@code {scene:String}}","description":"获取 iot 相关信息","example":["{@code null }"],"inputData":["JsonString: {@code {type:String,description:String,deviceModel:String}}"],"see":["#getJsModuleInterfaceName()"],"supportVersion":"305015000","sync":"true"}]
         * className : MiotAcceptJsModule
         * description : Miot 相关接口
         * extends : ["MiotAcceptJsModule"]
         * moduleName : xiaoai_miot
         * moduleTitle : Miot 模块
         */

        private String className;
        private String description;
        private String moduleName;
        private String moduleTitle;
        private List<BridgeBean> bridges;
        @SerializedName("extends")
        private List<String> extendsX;

        public String getClassName() {
            return className;
        }

        public void setClassName(String className) {
            this.className = className;
        }

        public String getModuleTitle() {
            return moduleTitle;
        }

        public void setModuleTitle(String moduleTitle) {
            this.moduleTitle = moduleTitle;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public String getModuleName() {
            return moduleName;
        }

        public void setModuleName(String moduleName) {
            this.moduleName = moduleName;
        }

        public List<BridgeBean> getBridges() {
            return bridges;
        }

        public void setBridges(List<BridgeBean> bridges) {
            this.bridges = bridges;
        }

        public List<String> getExtendsX() {
            return extendsX;
        }

        public void setExtendsX(List<String> extendsX) {
            this.extendsX = extendsX;
        }

        public static class BridgeBean {
            /**
             * author : author
             * bridgeName : getMiotDeviceIntention
             * callbackCycle : null
             * callbackError : null
             * callbackFunction : ["functionName data:(json)","functionName2 data:(json)","functionName3 data:(json)"]
             * callbackResult : JsonString: {@code {scene:String}}
             * description : 获取 iot 相关信息
             * example : ["{@code null }"]
             * inputData : ["JsonString: {@code {type:String,description:String,deviceModel:String}}"]
             * see : ["#getJsModuleInterfaceName()"]
             * supportVersion : 305015000
             * sync : true
             */

            private String author;
            private String bridgeName;
            private String callbackCycle;
            private String callbackError;
            private String callbackResult;
            private String description;
            private String deprecated;
            private int supportVersion;
            private int deprecatedVersion;
            private boolean sync;
            private boolean onlyOnHtmlInitCall;
            private String inputData;
            private List<String> callbackFunction;
            private List<String> example;
            private List<String> see;
            private List<String> update;

            public String getDeprecated() {
                return deprecated;
            }

            public void setDeprecated(String deprecated) {
                this.deprecated = deprecated;
            }

            public int getSupportVersion() {
                return supportVersion;
            }

            public void setSupportVersion(int supportVersion) {
                this.supportVersion = supportVersion;
            }

            public int getDeprecatedVersion() {
                return deprecatedVersion;
            }

            public void setDeprecatedVersion(int deprecatedVersion) {
                this.deprecatedVersion = deprecatedVersion;
            }

            public boolean getSync() {
                return sync;
            }

            public void setSync(boolean sync) {
                this.sync = sync;
            }

            public boolean isOnlyOnHtmlInitCall() {
                return onlyOnHtmlInitCall;
            }

            public void setOnlyOnHtmlInitCall(boolean onlyOnHtmlInitCall) {
                this.onlyOnHtmlInitCall = onlyOnHtmlInitCall;
            }

            public List<String> getUpdate() {
                return update;
            }

            public void setUpdate(List<String> update) {
                this.update = update;
            }

            public String getAuthor() {
                return author;
            }

            public void setAuthor(String author) {
                this.author = author;
            }

            public String getBridgeName() {
                return bridgeName;
            }

            public void setBridgeName(String bridgeName) {
                this.bridgeName = bridgeName;
            }

            public String getCallbackCycle() {
                return callbackCycle;
            }

            public void setCallbackCycle(String callbackCycle) {
                this.callbackCycle = callbackCycle;
            }

            public String getCallbackError() {
                return callbackError;
            }

            public void setCallbackError(String callbackError) {
                this.callbackError = callbackError;
            }

            public String getCallbackResult() {
                return callbackResult;
            }

            public void setCallbackResult(String callbackResult) {
                this.callbackResult = callbackResult;
            }

            public String getDescription() {
                return description;
            }

            public void setDescription(String description) {
                this.description = description;
            }

            public List<String> getCallbackFunction() {
                return callbackFunction;
            }

            public void setCallbackFunction(List<String> callbackFunction) {
                this.callbackFunction = callbackFunction;
            }

            public List<String> getExample() {
                return example;
            }

            public void setExample(List<String> example) {
                this.example = example;
            }

            public String getInputData() {
                return inputData;
            }

            public void setInputData(String inputData) {
                this.inputData = inputData;
            }

            public List<String> getSee() {
                return see;
            }

            public void setSee(List<String> see) {
                this.see = see;
            }


        }
    }
}
