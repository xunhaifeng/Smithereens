package com.xunhaifeng.module.web;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;

import com.tencent.smtt.sdk.WebSettings;
import com.tencent.smtt.sdk.WebView;
import com.xunhaifeng.R;

public class WebActivity extends AppCompatActivity {

    private WebView mWebView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web);
        mWebView = findViewById(R.id.webview);
        initWebView();
        initIntent();
    }

    private void initIntent() {
        Intent intent = getIntent();
        String url = intent.getStringExtra("url");
        if(!TextUtils.isEmpty(url)){
            mWebView.loadUrl(url);
        }
    }

    private void initWebView() {
        WebSettings settings = mWebView.getSettings();
        settings.setJavaScriptEnabled(true);
    }
}
