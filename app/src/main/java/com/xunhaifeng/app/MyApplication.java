package com.xunhaifeng.app;

import android.app.Application;

import com.xunhaifeng.http.HttpRequest;

/**
 * @name Smithereens
 * @class name：com.xunhaifeng.app
 * @anthor xunhf
 * @email xunhf@inspur.com
 * @time 2017/9/1 13:53
 * @change
 * @chang time
 * @class describe
 */

public class MyApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        HttpRequest.init();
    }
}
