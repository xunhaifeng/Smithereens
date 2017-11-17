package com.xunhaifeng.base;

/**
 * @name Smithereens
 * @class name：com.xunhaifeng.base
 * @anthor xunhf
 * @email xunhf@inspur.com
 * @time 2017/8/25 11:07
 * @change
 * @chang time
 * @class MVP中的presenter层父类
 */

public interface BasePresenter<T extends BaseView> {
    void onSubscribe(T t);
    void unsubscribe();
}
