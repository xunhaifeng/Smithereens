package com.xunhaifeng.app;

/**
 * @version v0.0.1
 * @name Smithereens
 * @class name：com.xunhaifeng.app
 * @anthor xunhf
 * @email xunhf@inspur.com
 * @time 2017/9/13 19:41
 * @chang time
 * @class describe
 */

public interface ApiConfig {
    //android开发技术周报网址
     String MSG_URL="http://www.androidweekly.cn/";
    /**
     * 干货集中营 API 文档
     * 分类数据: http://gank.io/api/data/数据类型/请求个数/第几页
     *数据类型： 福利 | Android | iOS | 休息视频 | 拓展资源 | 前端 | all
     *请求个数： 数字，大于0
     *第几页：数字，大于0
     */
    String BASE_URL="http://gank.io/api/";
    String URL_FULI=BASE_URL+"data/福利/10/";
    String URL_ANDROID=BASE_URL+"data/Android/10/";
}
