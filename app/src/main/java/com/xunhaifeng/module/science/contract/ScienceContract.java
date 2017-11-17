package com.xunhaifeng.module.science.contract;

import com.xunhaifeng.base.BasePresenter;
import com.xunhaifeng.base.BaseView;
import com.xunhaifeng.module.science.model.ScienceBean;

/**
 * @version v0.0.1
 * @name Smithereens
 * @class name：com.xunhaifeng.module.science.contract
 * @anthor xunhf
 * @email xunhf@inspur.com
 * @time 2017/11/17 11:38
 * @chang time
 * @class describe
 */

public interface ScienceContract {
    /**
     * 方法抽象
     */
    interface SCView extends BaseView<Presenter> {
        void showData(ScienceBean scienceBean);
    }
    interface Presenter extends BasePresenter<SCView> {
        void getData(int page);
    }
}
