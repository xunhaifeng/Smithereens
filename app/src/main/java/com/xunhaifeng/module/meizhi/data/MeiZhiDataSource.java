package com.xunhaifeng.module.meizhi.data;

import io.reactivex.Observable;

/**
 * @name Smithereens
 * @class name：com.xunhaifeng.data.source
 * @anthor xunhf
 * @email xunhf@inspur.com
 * @time 2017/8/25 13:50
 * @change
 * @chang time
 * @class describe
 */

public interface MeiZhiDataSource {

    Observable<String> getDatas();
}
