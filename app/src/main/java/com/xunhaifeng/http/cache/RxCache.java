package com.xunhaifeng.http.cache;

import android.content.Context;

import com.xunhaifeng.http.FastJsonUtil;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.annotations.NonNull;
import io.reactivex.schedulers.Schedulers;


/**
 * Created by gfh on 2015/7/28.
 */
public class RxCache {

    private static ACache aCache;
    private static FastJsonUtil mFastJsonUtil;

    /**
     * 初始化 在application中调用
     */
    public static synchronized void init(Context context){
        if(aCache == null){
            aCache = ACache.get(context);
        }
        if(mFastJsonUtil == null){
            mFastJsonUtil = new FastJsonUtil();
        }
    }
    public static Observable<String> getRxCache(final String cacheKey){
        return Observable.<String>create(new ObservableOnSubscribe<String>() {
            @Override
            public void subscribe(@NonNull ObservableEmitter<String> e) throws Exception {
                try {
                    String cache = aCache.getAsString(cacheKey);
                    if (cache != null) {
                        e.onNext(cache);
                        e.onComplete();
                    } else {
                        e.onError(new Throwable("no cache"));
                    }
                } catch (Throwable t) {
                    e.onError(t);
                }
            }
        }).subscribeOn(Schedulers.io());
    }


    public static String getCache(String cacheKey){
        String cache = aCache.getAsString(cacheKey);
        return cache;
    }


    public static<T> Observable<T> getRxCacheModel(final String cacheKey ,final Class<T> clazz){
        return Observable.create(new ObservableOnSubscribe<T>() {
            @Override
            public void subscribe(@NonNull ObservableEmitter<T> emitter) throws Exception {
                String cache = getCache(cacheKey);
                if (cache == null) {
                    emitter.onError(new Throwable("no cache"));
                } else {
                    try {
                        T model = FastJsonUtil.getObject(cache, clazz);
                        emitter.onNext(model);
                        emitter.onComplete();
                    } catch (Exception e) {
                        emitter.onError(new Throwable("get cache error"));
                    }
                }
            }
        }).subscribeOn(Schedulers.io());
    }


    public static void putCache(String catcheKey, String  str){
        aCache.put(catcheKey,str);
    }

}
