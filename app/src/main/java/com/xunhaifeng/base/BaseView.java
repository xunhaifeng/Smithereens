package com.xunhaifeng.base;

/**
 * @name Smithereens
 * @class name：com.xunhaifeng.base
 * @anthor xunhf
 * @email xunhf@inspur.com
 * @time 2017/8/25 11:06
 * @change
 * @chang time
 * @class MVP中的父View
 */

public interface BaseView<T> {
    void setPresenter(T presenter);
    void showLoading();
    void hiddenLoading();
}
