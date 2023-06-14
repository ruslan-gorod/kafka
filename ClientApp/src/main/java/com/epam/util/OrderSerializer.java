package com.epam.util;

import com.epam.entity.Order;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.kafka.common.errors.SerializationException;
import org.apache.kafka.common.serialization.Serializer;

import java.util.Map;

public class OrderSerializer implements Serializer<Order> {
    @Override
    public void configure(Map<String, ?> configs, boolean isKey) {
        Serializer.super.configure(configs, isKey);
    }

    @Override
    public byte[] serialize(String topic, Order order) {
        try {
            if (order == null) {
                return null;
            }
            return new ObjectMapper().writeValueAsBytes(order);
        } catch (Exception e) {
            throw new SerializationException("Error when serializing Order to byte[]");
        }
    }

    @Override
    public void close() {
        Serializer.super.close();
    }
}
