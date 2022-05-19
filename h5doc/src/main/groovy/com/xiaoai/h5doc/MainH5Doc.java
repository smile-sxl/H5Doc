package com.xiaoai.h5doc;

import org.gradle.api.Plugin;
import org.gradle.api.Project;

import java.util.ArrayList;
import java.util.List;


class MainH5Doc implements Plugin<Project> {
    public void apply(Project project) {
        startApply();
    }

    private void startApply() {
        System.out.println("------------------------");
        System.out.println("apply HelloGradlePlugin");
        System.out.println("------------------------");
        List<String> list = new ArrayList<String>();
        System.out.println("------------------------" + System.getProperty("user.dir"));
        list.add(System.getProperty("user.dir") + "/business/src/main/java/com/xiaomi/voiceassistant/web/module/accept/ActivityAcceptJsModule.java");
        list.add(System.getProperty("user.dir") + "/business/src/main/java/com/xiaomi/voiceassistant/web/module/accept/AdsAcceptJsModule.java");
        list.add(System.getProperty("user.dir") + "/business/src/main/java/com/xiaomi/voiceassistant/web/module/accept/AppAcceptJsModule.java");
        list.add(System.getProperty("user.dir") + "/business/src/main/java/com/xiaomi/voiceassistant/web/module/accept/BasicAcceptJsModule.java");
        list.add(System.getProperty("user.dir") + "/business/src/main/java/com/xiaomi/voiceassistant/web/module/accept/BluetoothAcceptJsModule.java");
//        list.add(System.getProperty("user.dir") + "/business/src/main/java/com/xiaomi/voiceassistant/web/module/accept/BoardAcceptJsModule.kt");
        list.add(System.getProperty("user.dir") + "/business/src/main/java/com/xiaomi/voiceassistant/web/module/accept/CardAcceptJsModule.java");
        list.add(System.getProperty("user.dir") + "/business/src/main/java/com/xiaomi/voiceassistant/web/module/accept/DialogAcceptJsModule.java");
        list.add(System.getProperty("user.dir") + "/business/src/main/java/com/xiaomi/voiceassistant/web/module/accept/DeviceAcceptJsModule.java");
        list.add(System.getProperty("user.dir") + "/business/src/main/java/com/xiaomi/voiceassistant/web/module/accept/HttpAcceptJsModule.java");
        list.add(System.getProperty("user.dir") + "/business/src/main/java/com/xiaomi/voiceassistant/web/module/accept/InstructionAcceptJsModule.java");
        list.add(System.getProperty("user.dir") + "/business/src/main/java/com/xiaomi/voiceassistant/web/module/accept/LocationAcceptJsModule.java");
        list.add(System.getProperty("user.dir") + "/business/src/main/java/com/xiaomi/voiceassistant/web/module/accept/MediaAcceptJsModule.java");
        list.add(System.getProperty("user.dir") + "/business/src/main/java/com/xiaomi/voiceassistant/web/module/accept/MiotAcceptJsModule.java");
        list.add(System.getProperty("user.dir") + "/business/src/main/java/com/xiaomi/voiceassistant/web/module/accept/PermissionAcceptJsModule.java");
//        list.add(System.getProperty("user.dir") + "/business/src/main/java/com/xiaomi/voiceassistant/web/module/accept/SettingsAcceptJsModule.kt");
        list.add(System.getProperty("user.dir") + "/business/src/main/java/com/xiaomi/voiceassistant/web/module/accept/ShareAcceptJsModule.java");
        list.add(System.getProperty("user.dir") + "/business/src/main/java/com/xiaomi/voiceassistant/web/module/accept/UtilAcceptJsModule.java");
        list.add(System.getProperty("user.dir") + "/business/src/main/java/com/xiaomi/voiceassistant/web/module/accept/V5MainContAcceptJsModule.java");
        list.add(System.getProperty("user.dir") + "/business/src/main/java/com/xiaomi/voiceassistant/web/module/accept/WebInitAcceptJsModule.java");

//        val safe = System.getProperty("user.dir") + "base/commonweb/src/main/java/com/xiaomi/voiceassistant/commonweb/jsbridge/module/accept/safe/SafeWebAcceptJsModule.java";
//        list.add(safe)
//        ApiDocGenerator.start(list);
    }


}