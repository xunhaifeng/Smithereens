package com.xunhaifeng.http;

import com.alibaba.fastjson.JSON;

import java.util.ArrayList;
import java.util.List;

/**
 * @name Smithereens
 * @class name：com.xunhaifeng.http
 * @anthor xunhf
 * @email xunhf@inspur.com
 * @time 2017/9/1 9:33
 * @change
 * @chang time
 * @class alibaba快速解析工具类
 */

public class FastJsonUtil {
    public static <T> T getObject(String jsonString, Class<T> cls) {
        T t = null;
        try {
            t = JSON.parseObject(jsonString, cls);
        } catch (Exception e) {
            // TODO: handle exception
        }
        return t;
    }

    public static<T> List<T> getArray(String jsonString, Class<T> cls) {
        List<T> list = new ArrayList<>();
        try {
            list = JSON.parseArray(jsonString, cls);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }
}
