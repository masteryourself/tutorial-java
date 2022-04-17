package org.masteryourself.tutorial.netty.rpc.protocol;

import com.google.gson.*;

import java.io.*;
import java.lang.reflect.Type;
import java.nio.charset.StandardCharsets;

/**
 * <p>description : SerializerAlgorithm
 *
 * <p>blog : https://www.yuque.com/ruanrenzhao/
 *
 * @author : masteryourself
 * @version : 1.0.0
 * @date : 2022/4/17 9:44 PM
 */
public enum SerializerAlgorithm implements Serializer {

    JAVA {
        @Override
        public <T> T deserialize(Class<T> clazz, byte[] bytes) {
            try {
                ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(bytes);
                return (T) new ObjectInputStream(byteArrayInputStream).readObject();
            } catch (Exception e) {
                throw new RuntimeException("JAVA deserialize error", e);
            }
        }

        @Override
        public <T> byte[] serialize(T object) {
            try {
                ByteArrayOutputStream buffer = new ByteArrayOutputStream();
                new ObjectOutputStream(buffer).writeObject(object);
                return buffer.toByteArray();
            } catch (IOException e) {
                throw new RuntimeException("JAVA serializer error", e);
            }
        }
    },

    GSON {

        private final Gson gson = new GsonBuilder().registerTypeAdapter(Class.class, new ClassCodec()).create();

        @Override
        public <T> T deserialize(Class<T> clazz, byte[] bytes) {
            return gson.fromJson(new String(bytes, StandardCharsets.UTF_8), clazz);
        }

        @Override
        public <T> byte[] serialize(T object) {
            return gson.toJson(object).getBytes(StandardCharsets.UTF_8);
        }
    };

    public static SerializerAlgorithm getByType(Byte type) {
        return SerializerAlgorithm.values()[type];
    }

    public static class ClassCodec implements JsonSerializer<Class<?>>, JsonDeserializer<Class<?>> {

        @Override
        public Class<?> deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
            try {
                String str = json.getAsString();
                return Class.forName(str);
            } catch (ClassNotFoundException e) {
                throw new JsonParseException(e);
            }
        }

        @Override
        public JsonElement serialize(Class<?> src, Type typeOfSrc, JsonSerializationContext context) {
            // class -> json
            return new JsonPrimitive(src.getName());
        }
    }

}
