package com.illustration.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;

public class JsonUtil {
    private static final ObjectMapper objectMapper = new ObjectMapper();

    static {
        // 配置在反序列化时忽略未知的属性
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
    }

    // 将对象序列化为JSON字符串
    public static String serialize(Object object) {
        try {
            return objectMapper.writeValueAsString(object);
        } catch (JsonProcessingException e) {
            throw new RuntimeException("Failed to serialize object to JSON", e);
        }
    }

    // 将对象序列话为字节序列
    public static byte[] serializeToByte(Object object) {
        try {
            return objectMapper.writeValueAsBytes(object);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    // 将JSON字符串反序列化为指定类型的对象
    public static <T> T deserialize(String json, Class<T> valueType) {
        try {
            return objectMapper.readValue(json, valueType);
        } catch (IOException e) {
            throw new RuntimeException("Failed to deserialize JSON to object", e);
        }
    }

    public static <T> T deserialize(byte[] bytes, Class<T> valueType) {
        try {
            return objectMapper.readValue(bytes, valueType);
        } catch (IOException e) {
            throw new RuntimeException("Failed to deserialize JSON to object", e);
        }
    }
}
