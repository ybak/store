package util;

import com.google.gson.ExclusionStrategy;
import com.google.gson.FieldAttributes;

public class GsonExclusionStrategy implements ExclusionStrategy {

    @Override
    public boolean shouldSkipClass(Class<?> clazz) {
        return clazz.getAnnotation(GsonExclude.class) != null;
    }

    @Override
    public boolean shouldSkipField(FieldAttributes f) {
        return f.getName().equals("persistent") || f.getName().equals("entityId")
                || f.getAnnotation(GsonExclude.class) != null;
    }

}