package com.xunhaifeng.meizhi.data;

import android.support.annotation.Nullable;

import com.xunhaifeng.http.HttpRequest;
import com.xunhaifeng.meizhi.contract.MeiZhiContract;

import io.reactivex.Observable;
import okhttp3.Request;

/**
 * @name Smithereens
 * @class name：com.xunhaifeng.meizhi.data
 * @anthor xunhf
 * @email xunhf@inspur.com
 * @time 2017/8/25 15:07
 * @change
 * @chang time
 * @class describe
 */

public class MeizhiRepository implements MeiZhiContract.MeiZhiDataSource {
    @Nullable
    private static MeizhiRepository INSTANCE = null;

    public static MeizhiRepository getInstance(){

        if(INSTANCE == null){
            INSTANCE = new MeizhiRepository();
        }
        return INSTANCE;
    }

    @Override
    public Observable<String> getNetData(int page) {
        Request request = new Request.Builder().url("http://gank.io/api/data/福利/10/"+page)
                .get()
                .build();
        return HttpRequest.stringRequest(request,HttpRequest.RequestType.USE_NET_ONLY, null);
    }
}
