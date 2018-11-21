package com.chen.amqp.util;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;

/**
 * JsonUtils
 *
 * @Author LeifChen
 * @Date 2018-11-21
 */
@Slf4j
public class JsonUtils {

    /**
     * 将对象转为 JSON 字符串
     *
     * @param obj 任意对象
     * @return JSON 字符串
     */
    public static String convertObjectToJson(Object obj) {
        try {
            return JSON.toJSONString(obj);
        } catch (Exception ex) {
            log.warn("将对象转为JSON字符串异常：" + ex);
            throw new RuntimeException("将对象转为JSON字符串异常：" + ex.getMessage(), ex);
        }
    }

    /**
     * 将 JSON 字符串转为对象
     *
     * @param message JSON 字符串
     * @param type    对象
     * @param <T>     对象
     * @return 对象实例
     */
    public static <T> T convertJsonToObject(String message, Class<T> type) {
        try {
            return JSONObject.parseObject(message, type);
        } catch (Exception ex) {
            log.warn("将JSON字符串转为对象异常：" + ex);
            throw new RuntimeException("将JSON字符串转为对象异常：" + ex.getMessage(), ex);
        }
    }
}
