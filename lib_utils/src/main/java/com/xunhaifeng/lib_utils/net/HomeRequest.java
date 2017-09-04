package com.xunhaifeng.lib_utils.net;

import com.xunhaifeng.lib_utils.data.MeiZhiData;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * @name Smithereens
 * @class name：com.xunhaifeng.lib_utils.net
 * @anthor xunhf
 * @email xunhf@inspur.com
 * @time 2017/8/24 11:29
 * @change
 * @chang time
 * @class 首页接口封装，retrofit测试用
 */

public interface HomeRequest {
    @GET("data/福利//{page}")
    Observable<MeiZhiData> getMeizhiData(@Path("page") int page);
}
