package com.epam.util;

import com.epam.entity.Order;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.kafka.common.errors.SerializationException;
import org.apache.kafka.common.serialization.Deserializer;

import java.nio.charset.StandardCharsets;
import java.util.Map;

public class OrderDeserializer implements Deserializer<Order> {
    @Override
    public void configure(Map<String, ?> configs, boolean isKey) {
        Deserializer.super.configure(configs, isKey);
    }

    @Override
    public Order deserialize(String topic, byte[] bytes) {
        try {
            if (bytes == null){
                return null;
            }
            return new ObjectMapper().readValue(new String(bytes, StandardCharsets.UTF_8), Order.class);
        } catch (Exception e) {
            throw new SerializationException("Error when deserializing byte[] to Order");
        }
    }

    @Override
    public void close() {
        Deserializer.super.close();
    }
}
