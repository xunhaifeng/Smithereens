package com.xunhaifeng.lib_utils.net;

import android.util.Log;

import com.xunhaifeng.lib_utils.data.MeiZhiData;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;

/**
 * @name Smithereens
 * @class name：com.xunhaifeng.lib_utils.net
 * @anthor xunhf
 * @email xunhf@inspur.com
 * @time 2017/8/25 10:25
 * @change
 * @chang time
 * @class retrofit 工具类
 */

public class HttpRetrofit {
    public static void getInstance(){

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://gank.io/api/")
                .build();
        HomeRequest request = retrofit.create(HomeRequest.class);
        Observable<MeiZhiData> meizhiData = request.getMeizhiData(1);
        meizhiData.observeOn(Schedulers.io())
                .subscribeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<MeiZhiData>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {

                    }

                    @Override
                    public void onNext(@NonNull MeiZhiData meiZhiData) {
                        Log.w("tag","meiZhiData="+meiZhiData);
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }
}
