package com.xunhaifeng.module.science.model;

import java.util.List;

/**
 * @version v0.0.1
 * @name Smithereens
 * @class name：com.xunhaifeng.module.science.model
 * @anthor xunhf
 * @email xunhf@inspur.com
 * @time 2017/11/17 14:11
 * @chang time
 * @class describe
 */

public class ScienceBean {

    /**
     * error : false
     * results : [{"_id":"59fbc52d421aa90fe72535f0","createdAt":"2017-11-03T09:23:57.190Z","desc":"深入浅出 RxJava2 ","publishedAt":"2017-11-17T12:39:48.189Z","source":"web","type":"Android","url":"http://www.jianshu.com/u/f92ca7c0d2df","used":true,"who":"Kai Sun"},{"_id":"5a0d1455421aa90fe2f02c62","createdAt":"2017-11-16T12:30:13.222Z","desc":"在线看Android 源码原来是如此的方便","publishedAt":"2017-11-17T12:39:48.189Z","source":"chrome","type":"Android","url":"https://mp.weixin.qq.com/s?__biz=MzIwODI3MTc2Ng==&mid=2649647474&idx=1&sn=22c4f8d3dace6094f8f0d00cafea5483&chksm=8f1f698bb868e09d1ea5019cdcdd659e7a0a156011f2c0bdabf56efba3445c727877c6ca6b2e#rd","used":true,"who":"技术特工队"},{"_id":"5a0e3c92421aa90fe7253641","createdAt":"2017-11-17T09:34:10.443Z","desc":"华为如何实现基于Git的跨地域协同开发","publishedAt":"2017-11-17T12:39:48.189Z","source":"web","type":"Android","url":"https://mp.weixin.qq.com/s?__biz=MzIwMzYwMTk1NA==&mid=2247488268&idx=1&sn=9e908855b84df9db6fdaf74546de7591","used":true,"who":"陈宇明"},{"_id":"5a0e417a421aa90fef20353c","createdAt":"2017-11-17T09:55:06.514Z","desc":"CacheWebView","publishedAt":"2017-11-17T12:39:48.189Z","source":"chrome","type":"Android","url":"https://github.com/yale8848/CacheWebView?utm_source=gold_browser_extension","used":true,"who":"Jason"},{"_id":"5a0e66f8421aa90fe7253644","createdAt":"2017-11-17T12:35:04.382Z","desc":"把 IntelliJ 的自动完成算法填到 Vim 中。","publishedAt":"2017-11-17T12:39:48.189Z","source":"chrome","type":"Android","url":"https://github.com/google/ijaas","used":true,"who":"代码家"},{"_id":"5a0b9888421aa90fef203530","createdAt":"2017-11-15T09:29:44.280Z","desc":"Android与Python爱之初体验","publishedAt":"2017-11-16T12:01:05.619Z","source":"web","type":"Android","url":"https://mp.weixin.qq.com/s?__biz=MzIwMzYwMTk1NA==&mid=2247488220&idx=1&sn=834f2b928d2852ebdc4db1439ee0e08b","used":true,"who":"陈宇明"},{"_id":"5a0bcf5a421aa90fe725363c","createdAt":"2017-11-15T13:23:38.991Z","desc":"[开发利器]在线查看对比 Android 和 Java 任意版本源码 IDEA插件","images":["http://img.gank.io/a3fc2a25-adea-45de-b186-17884187280c"],"publishedAt":"2017-11-16T12:01:05.619Z","source":"web","type":"Android","url":"https://github.com/pengwei1024/AndroidSourceViewer","used":true,"who":"舞影凌风"},{"_id":"5a0ce9b4421aa90fe2f02c5d","createdAt":"2017-11-16T09:28:20.728Z","desc":"CacheWebView通过拦截静态资源实现内存(LRU)和磁盘(LRU)2级缓存。突破系统WebView缓存的空间限制，让缓存更简单、更快、更灵活。让网站离线也能正常访问。","images":["http://img.gank.io/c2797609-468f-45bd-b7dc-8296d10a1423"],"publishedAt":"2017-11-16T12:01:05.619Z","source":"web","type":"Android","url":"https://github.com/yale8848/CacheWebView","used":true,"who":"Yale"},{"_id":"5a0cfd99421aa90fe2f02c5e","createdAt":"2017-11-16T10:53:13.537Z","desc":"论热修复实战心得\u2014很值得学习的热修复详解|强烈推荐","publishedAt":"2017-11-16T12:01:05.619Z","source":"web","type":"Android","url":"http://mp.weixin.qq.com/s?__biz=MzI3OTU0MzI4MQ==&mid=100001255&idx=1&sn=6b1674c7578039b61b4c34825619c363&chksm=6b4769795c30e06fa1d02f89e7a3e230c2d9c5761b0256fd1ed33eee899803a95f574a144450#rd","used":true,"who":"codeGoogler"},{"_id":"5a0d00be421aa90fef203534","createdAt":"2017-11-16T11:06:38.136Z","desc":"可能是最好看的 Android 圆形 Menu 菜单效果。","publishedAt":"2017-11-16T12:01:05.619Z","source":"chrome","type":"Android","url":"https://github.com/Ramotion/circle-menu-android","used":true,"who":"代码家"}]
     */

    private boolean error;
    private List<ResultsBean> results;

    public boolean isError() {
        return error;
    }

    public void setError(boolean error) {
        this.error = error;
    }

    public List<ResultsBean> getResults() {
        return results;
    }

    public void setResults(List<ResultsBean> results) {
        this.results = results;
    }

    public static class ResultsBean {
        /**
         * _id : 59fbc52d421aa90fe72535f0
         * createdAt : 2017-11-03T09:23:57.190Z
         * desc : 深入浅出 RxJava2
         * publishedAt : 2017-11-17T12:39:48.189Z
         * source : web
         * type : Android
         * url : http://www.jianshu.com/u/f92ca7c0d2df
         * used : true
         * who : Kai Sun
         * images : ["http://img.gank.io/a3fc2a25-adea-45de-b186-17884187280c"]
         */

        private String _id;
        private String createdAt;
        private String desc;
        private String publishedAt;
        private String source;
        private String type;
        private String url;
        private boolean used;
        private String who;
        private List<String> images;

        public String get_id() {
            return _id;
        }

        public void set_id(String _id) {
            this._id = _id;
        }

        public String getCreatedAt() {
            return createdAt;
        }

        public void setCreatedAt(String createdAt) {
            this.createdAt = createdAt;
        }

        public String getDesc() {
            return desc;
        }

        public void setDesc(String desc) {
            this.desc = desc;
        }

        public String getPublishedAt() {
            return publishedAt;
        }

        public void setPublishedAt(String publishedAt) {
            this.publishedAt = publishedAt;
        }

        public String getSource() {
            return source;
        }

        public void setSource(String source) {
            this.source = source;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public boolean isUsed() {
            return used;
        }

        public void setUsed(boolean used) {
            this.used = used;
        }

        public String getWho() {
            return who;
        }

        public void setWho(String who) {
            this.who = who;
        }

        public List<String> getImages() {
            return images;
        }

        public void setImages(List<String> images) {
            this.images = images;
        }
    }
}
