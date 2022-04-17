package org.masteryourself.tutorial.netty.chat.protocol;

import com.google.gson.Gson;

import java.io.*;
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

        private final Gson gson = new Gson();

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

}
