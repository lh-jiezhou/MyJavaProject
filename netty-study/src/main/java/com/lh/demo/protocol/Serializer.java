package com.lh.demo.protocol;

import com.google.gson.Gson;
import com.lh.demo.message.Message;

import java.io.*;
import java.nio.charset.StandardCharsets;

/**
 * 用于扩展序列化、反序列化算法
 * @author
 */
public interface Serializer {

    /**
     * 反序列化方法 字节数组 还原为 java对象
     * @param clazz
     * @param bytes
     * @param <T>
     * @return
     */
    <T> T deserialize(Class<T> clazz, byte[] bytes);

    /**
     * 序列化方法 java 对象转为 字节数组
     * @param object
     * @param <T>
     * @return
     */
    <T> byte[] serialize(T object);


    /**
     * 枚举 具体算法实现
     *      每个元素即一个对象
     */
    enum Algorithm implements Serializer{

        // 序号0, Java
        Java {
            @Override
            public <T> T deserialize(Class<T> clazz, byte[] bytes) {
                try {
                    ObjectInputStream ois = new ObjectInputStream(new ByteArrayInputStream(bytes));
                    return (T) ois.readObject();
                } catch (IOException | ClassNotFoundException e) {
                    // 暂不处理
                    throw new RuntimeException("序列化失败", e);
                }
            }
            @Override
            public <T> byte[] serialize(T object) {
                try {
                    ByteArrayOutputStream bos = new ByteArrayOutputStream();
                    ObjectOutputStream oos = new ObjectOutputStream(bos);
                    oos.writeObject(object);
                    return bos.toByteArray();
                } catch (IOException e) {
                    throw new RuntimeException("序列化失败", e);
                }
            }
        },

        // 序号1, Json
        Json {
            @Override
            public <T> T deserialize(Class<T> clazz, byte[] bytes) {
                String json = new String(bytes, StandardCharsets.UTF_8);
                // Json串转对象
                return new Gson().fromJson(json, clazz);
            }

            @Override
            public <T> byte[] serialize(T object) {
                // 对象先转Json字符串
                String json = new Gson().toJson(object);
                return json.getBytes(StandardCharsets.UTF_8);
            }
        }
    }


}
