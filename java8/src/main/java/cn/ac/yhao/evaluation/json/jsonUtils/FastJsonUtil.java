package cn.ac.yhao.evaluation.json.jsonUtils;

import com.alibaba.fastjson2.JSON;

/**
 * @description: fastjson
 * @author: Daniel Young
 * @create: 2021-08-05 09:45
 */
public class FastJsonUtil {
    public static String bean2Json(Object obj) {
        return JSON.toJSONString(obj);
    }

    public static <T> T json2Bean(String jsonStr, Class<T> objClass) {
        return JSON.parseObject(jsonStr, objClass);
    }
}
