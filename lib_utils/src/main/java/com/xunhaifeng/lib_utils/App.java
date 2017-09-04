package com.xunhaifeng.lib_utils;

import android.app.Application;
import android.content.Context;

import com.litesuits.orm.LiteOrm;

/**
 * @name Smithereens
 * @class name：com.xunhaifeng.lib_utils
 * @anthor xunhf
 * @email xunhf@inspur.com
 * @time 2017/8/25 9:55
 * @change
 * @chang time
 * @class describe
 */

public class App extends Application {
    private static final String DB_NAME = "gank.db";
    public static Context sContext;
    public static LiteOrm sDb;
    @Override
    public void onCreate() {
        super.onCreate();
        sDb = LiteOrm.newSingleInstance(this, DB_NAME);
        sDb.setDebugged(true);
    }
}
