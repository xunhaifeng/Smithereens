package com.xunhaifeng.module.science.presenter;

import com.xunhaifeng.app.ApiConfig;
import com.xunhaifeng.http.HttpRequest;
import com.xunhaifeng.module.science.contract.ScienceContract;
import com.xunhaifeng.module.science.model.ScienceBean;
import com.xunhaifeng.module.science.view.ScienceFragment;

import io.reactivex.android.schedulers.AndroidSchedulers;
import okhttp3.Request;

/**
 * @version v0.0.1
 * @name Smithereens
 * @class name：com.xunhaifeng.module.science.presenter
 * @anthor xunhf
 * @email xunhf@inspur.com
 * @time 2017/11/17 11:40
 * @chang time
 * @class describe
 */

public class SciencePresenter implements ScienceContract.Presenter {

    private ScienceFragment mScienceFragment;

    @Override
    public void onSubscribe(ScienceContract.SCView scView) {
        mScienceFragment = (ScienceFragment)scView;
    }

    @Override
    public void unsubscribe() {

    }

    @Override
    public void getData(int page) {
        Request request = new Request.Builder().url(ApiConfig.URL_ANDROID+page)
                .get()
                .build();
        HttpRequest.modelRequest(request, HttpRequest.RequestType.USE_NET_ONLY,"", ScienceBean.class)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(scienceBean -> {
                    mScienceFragment.showData(scienceBean);
                },throwable -> {
                    mScienceFragment.showData(null);
                });
    }
}
