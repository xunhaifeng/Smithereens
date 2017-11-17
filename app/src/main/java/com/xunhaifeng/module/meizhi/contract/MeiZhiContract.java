package com.xunhaifeng.module.meizhi.contract;

import com.xunhaifeng.base.BasePresenter;
import com.xunhaifeng.base.BaseView;

import io.reactivex.Observable;


/**
 * @name Smithereens
 * @class name：com.xunhaifeng.module.meizhi
 * @anthor xunhf
 * @email xunhf@inspur.com
 * @time 2017/8/25 11:09
 * @change
 * @chang time
 * @class 协议类XXXContract来对View和Presenter的接口进行继承
 */

public interface MeiZhiContract {
    /**
     * 方法抽象
     */
    interface View< T > extends BaseView<Presenter>{
        void showList(T t);
    }
    interface Presenter extends BasePresenter{
        void getData(int page);
    }

    interface MeiZhiDataSource {
        Observable<String> getNetData(int page);
    }
}
