package com.xunhaifeng.app;

import android.app.Application;
import android.content.Context;
import android.util.Log;

import com.taobao.sophix.PatchStatus;
import com.taobao.sophix.SophixManager;
import com.taobao.sophix.listener.PatchLoadStatusListener;
import com.tencent.smtt.sdk.QbSdk;
import com.xunhaifeng.BuildConfig;
import com.xunhaifeng.R;
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
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        initHotFix();
    }

    private void initHotFix() {
// initialize最好放在attachBaseContext最前面，初始化直接在Application类里面，切勿封装到其他类
        SophixManager.getInstance().setContext(this)
                .setAppVersion(BuildConfig.VERSION_NAME)
                .setSecretMetaData(getString(R.string.hotfix_idSecret),getString(R.string.hotfix_appSecret),getString(R.string.hotfix_rsaSecret))
                .setEnableDebug(true)
                .setPatchLoadStatusStub(new PatchLoadStatusListener() {
                    @Override
                    public void onLoad(final int mode, final int code, final String info, final int handlePatchVersion) {
                        // 补丁加载回调通知
                        if (code == PatchStatus.CODE_LOAD_SUCCESS) {
                            // 表明补丁加载成功
                        } else if (code == PatchStatus.CODE_LOAD_RELAUNCH) {
                            // 表明新补丁生效需要重启. 开发者可提示用户或者强制重启;
                            // 建议: 用户可以监听进入后台事件, 然后调用killProcessSafely自杀，以此加快应用补丁，详见1.3.2.3
                        } else {
                            // 其它错误信息, 查看PatchStatus类说明
                        }
                    }
                }).initialize();
        // queryAndLoadNewPatch不可放在attachBaseContext 中，否则无网络权限，建议放在后面任意时刻，如onCreate中
        //SophixManager.getInstance().queryAndLoadNewPatch();
    }

    @Override
    public void onCreate() {
        super.onCreate();
        // queryAndLoadNewPatch不可放在attachBaseContext 中，否则无网络权限，建议放在后面任意时刻，如onCreate中
        SophixManager.getInstance().queryAndLoadNewPatch();
        HttpRequest.init();
        //x5内核初始化接口
        QbSdk.PreInitCallback cb = new QbSdk.PreInitCallback() {

            @Override
            public void onViewInitFinished(boolean arg0) {
                //x5內核初始化完成的回调，为true表示x5内核加载成功，否则表示x5内核加载失败，会自动切换到系统内核。
                Log.d("app", " onViewInitFinished is " + arg0);
            }

            @Override
            public void onCoreInitFinished() {
            }
        };
        //x5内核初始化接口
        QbSdk.initX5Environment(getApplicationContext(),  cb);

    }
}
