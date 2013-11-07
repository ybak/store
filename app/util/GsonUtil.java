package util;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;

public class GsonUtil {

    private static Gson gson = new GsonBuilder().setExclusionStrategies(new GsonExclusionStrategy()).create();

    public static String toJson(Object src) {
        return gson.toJson(src);
    }
}
