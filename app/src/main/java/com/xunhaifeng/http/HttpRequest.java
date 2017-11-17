package com.xunhaifeng.http;

import android.util.Log;

import com.xunhaifeng.http.cache.RxCache;

import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.schedulers.Schedulers;
import okhttp3.CookieJar;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by gfh on 2015/7/28.
 */
public class HttpRequest {

    private static OkHttpClient okHttpClient;

    public enum RequestType {
        USE_CACHE_AND_NET,/////先读取换存，等网络数据请求下来后返回网络数据 并缓存
        USE_NET_ONLY ////只读取网络数据不读缓存也不进行缓存
    }

    private static FastJsonUtil mFastJsonUtil;


    /**
     * 初始化 在application中调用
     */
    public static synchronized void init() {
        if (okHttpClient == null) {
            okHttpClient = new OkHttpClient.Builder()
                    .connectTimeout(8,TimeUnit.SECONDS)
                    .readTimeout(10,TimeUnit.SECONDS)
                    .writeTimeout(10,TimeUnit.SECONDS)
                    .build();
        }
        if (mFastJsonUtil == null) {
            mFastJsonUtil = new FastJsonUtil();
        }
    }


    /**
     * 初始化 在application中调用
     */
    public static synchronized void init(CookieJar cookieJar) {
        if (okHttpClient == null) {
            okHttpClient = new OkHttpClient.Builder()
                    .connectTimeout(8,TimeUnit.SECONDS)
                    .readTimeout(10,TimeUnit.SECONDS)
                    .writeTimeout(10,TimeUnit.SECONDS)
                    .cookieJar(cookieJar)
                    .build();
        }
        if (mFastJsonUtil == null) {
            mFastJsonUtil = new FastJsonUtil();
        }
    }

    /**
     * 请求
     * @param request
     * @param requestType
     * @param cacheKey
     * @param clazz
     * @param <T>
     * @return
     */
    public static <T> Observable<T> modelRequest(final Request request, final RequestType requestType, final String cacheKey, final Class<T> clazz) {
        return Observable.create(new ObservableOnSubscribe<T>() {
            @Override
            public void subscribe(@NonNull ObservableEmitter<T> observableEmitter) throws Exception {
                switch (requestType) {
                    case USE_CACHE_AND_NET:
                        String mCache = RxCache.getCache(cacheKey);
                        //读取缓存
                        if(mCache!=null){
                            T t = FastJsonUtil.getObject(mCache,clazz);
                            observableEmitter.onNext(t);
                        }
                        //获取网络请求的数据
                        Response response = okHttpClient.newCall(request).execute();
                        if (response.code() == 200) {
                            String mResponse = response.body().string();
                            request.url();
                            T model = FastJsonUtil.getObject(mResponse, clazz);
                            observableEmitter.onNext(model);
                            observableEmitter.onComplete();
                            RxCache.putCache(cacheKey, mResponse);
                        }else{
                            Throwable e = new Throwable("请求失败");
                            observableEmitter.onError(e);
                        }
                        break;
                    case USE_NET_ONLY:
                        //获取网络请求的数据
                        response = okHttpClient.newCall(request).execute();
                        if (response.code() == 200) {
                            String mResponse = response.body().string();
                            request.url();
                            T model = FastJsonUtil.getObject(mResponse, clazz);
                            observableEmitter.onNext(model);
                            observableEmitter.onComplete();
                        }else{
                            Throwable e = new Throwable("请求失败");
                            observableEmitter.onError(e);
                        }
                        break;
                }
            }
        })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }
//    public static <T> Observable<T> modelRequest2(final Request request, final RequestType requestType, final String cacheKey, final Class<T> clazz) {
//        return ;
//    }
//
//    public static <T> Observable<T> modelRequest(final Request request, final Class<T> clazz) {
//        return ;
//    }
//
    public static Observable<String> stringRequest(final Request request, final RequestType requestType, final String cacheKey) {
        return Observable.create(new ObservableOnSubscribe<String>() {
            @Override
            public void subscribe(@NonNull ObservableEmitter<String> observableEmitter) throws Exception {
                switch (requestType) {
                    case USE_CACHE_AND_NET: { ////先读取缓存数据再读取网络数据
                        try {
                            String cache = RxCache.getCache(cacheKey);
                            if (cache != null) {
                                observableEmitter.onNext(cache);
                            }
                            Response response = okHttpClient.newCall(request).execute();
                            if (response.code() == 200) {
                                String result = response.body().string();
                                Log.e("out====result", result);
                                observableEmitter.onNext(result);
                                observableEmitter.onComplete();
                                RxCache.putCache(cacheKey, result);
                            } else {
                                Throwable e = new Throwable("请求失败");
                                observableEmitter.onError(e);
                            }


                        } catch (Throwable t) {
                            t.printStackTrace();
                            Throwable eS = new Throwable("请求失败");
                            observableEmitter.onError(eS);
                        }

                        break;
                    }
                    case USE_NET_ONLY: {
                        try {
                            Response response = okHttpClient.newCall(request).execute();
                            if (response.code() == 200) {
                                String result = response.body().string();
                                Log.e("tag", "请求地址" + request.url() + result);
                                observableEmitter.onNext(result);
                                observableEmitter.onComplete();
                            } else {
                                Throwable e = new Throwable("请求失败");
                                observableEmitter.onError(e);
                            }
                        } catch (Throwable t) {
                            t.printStackTrace();
                            Throwable eS = new Throwable("请求失败");
                            observableEmitter.onError(eS);
                        }
                        break;
                    }
                }
            }

        }).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread());
    }
//
//    public static Observable<String> stringRequest2(final Request request, final RequestType requestType, final String cacheKey) {
//        return Observable.create().subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread());
//    }
//
//
//
//    public static <T> Observable<T> postFile(final Request request, final Class<T> clazz) {
//        return Observable.create().subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread());
//    }

}
