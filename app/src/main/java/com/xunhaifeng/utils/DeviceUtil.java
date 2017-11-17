package com.xunhaifeng.utils;

import android.os.Environment;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

/**
 * @version v0.0.1
 * @name Smithereens
 * @class name：com.xunhaifeng.utils
 * @anthor xunhf
 * @email xunhf@inspur.com
 * @time 2017/11/16 15:04
 * @chang time
 * @class 设备信息工具类
 */

public class DeviceUtil {

    private static final String KEY_MIUI_VERSION_CODE = "ro.miui.ui.version.code";
    private static final String KEY_MIUI_VERSION_NAME = "ro.miui.ui.version.name";
    private static final String KEY_MIUI_INTERNAL_STORAGE = "ro.miui.internal.storage";

    public static boolean isMIUI(){
        return getRomProduct(KEY_MIUI_VERSION_CODE) != null
                || getRomProduct(KEY_MIUI_VERSION_NAME) != null
                || getRomProduct(KEY_MIUI_INTERNAL_STORAGE) != null;
    }

    /**
     * 获取当前手机操作系统，需要考虑刷机的情况
     * @return
     */
    public static String getRomProduct(String name){
        try {
            Properties properties= new Properties();
            properties.load(new FileInputStream(new File(Environment.getRootDirectory(), "build.prop")));
            return properties.getProperty(name);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "other";
    }
}
