package cn.ac.yhao.evaluation.json.jsonUtils;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;

/**
 * @description: gson
 * @author: Daniel Young
 * @create: 2021-08-05 09:46
 */
public class GsonUtil {
    private static Gson gson = new GsonBuilder().create();
    private static Gson PrettyGson = new GsonBuilder().setPrettyPrinting().create();

    public static String bean2Json(Object obj) {
        return gson.toJson(obj);
    }

    public static <T> T json2Bean(String jsonStr, Class<T> objClass) {
        return gson.fromJson(jsonStr, objClass);
    }

    public static String jsonFormatter(String uglyJsonStr) {
        JsonElement je = JsonParser.parseString(uglyJsonStr);
        return PrettyGson.toJson(je);
    }
}
