package com.epam.service;

import com.epam.entity.Order;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class ClientKafkaListener {

    @Autowired
    private OrderService orderService;

    @KafkaListener(topics = "notification-topic", groupId = "client-group")
    public void orderListener(ConsumerRecord<String, Order> record) {
        orderService.update(record.value());
    }
}