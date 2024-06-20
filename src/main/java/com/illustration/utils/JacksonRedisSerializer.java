package com.illustration.utils;

import io.jsonwebtoken.io.SerializationException;
import lombok.SneakyThrows;
import org.springframework.data.redis.serializer.RedisSerializer;

public class JacksonRedisSerializer<T> implements RedisSerializer<T> {

    private Class<T> clazz;

    public JacksonRedisSerializer(Class<T> clazz) {
        super();
        this.clazz = clazz;
    }

    @SneakyThrows
    @Override
    public byte[] serialize(T value) throws SerializationException {
        if (value == null) {
            return new byte[0];
        }
        return JsonUtil.serializeToByte(value);
    }

    @Override
    public T deserialize(byte[] bytes) throws SerializationException {
        if (bytes == null || bytes.length == 0) {
            return null;
        }
        return JsonUtil.deserialize(bytes, clazz);
    }
}
