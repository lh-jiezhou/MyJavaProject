package com.lh.source;

import com.google.gson.*;
import com.lh.demo.protocol.Serializer;

import java.lang.reflect.Type;

/**
 * class 类型转 json 时出现的问题
 *
 */
public class TestGson {

    public static void main(String[] args) {
//        System.out.println(new Gson().toJson(String.class));

        Gson gson = new GsonBuilder().registerTypeAdapter(Class.class, new Serializer.ClassCodec()).create();
        // 使用创建的 gson 来转换
        System.out.println(gson.toJson(String.class));
    }

    /*static class ClassCodec implements JsonSerializer<Class<?>>, JsonDeserializer<Class<?>> {

        @Override
        public Class<?> deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
            // json -> class
            try {
                String str = json.getAsString();
                return Class.forName(str);
            } catch (ClassNotFoundException e) {
                throw new JsonParseException(e);
            }
        }

        @Override                   // String.class
        public JsonElement serialize(Class<?> src, Type typeOfSrc, JsonSerializationContext context) {
            // class -> json
            return new JsonPrimitive(src.getName());
        }
    }*/
}
