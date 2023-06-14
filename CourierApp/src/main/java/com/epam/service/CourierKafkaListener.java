package com.epam.service;

import com.epam.entity.Order;
import com.epam.entity.Status;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class CourierKafkaListener {

    @Autowired
    private OrderService orderService;

    @Autowired
    private KafkaMessageService kafkaMessageService;

    @KafkaListener(topics = "notification-topic", groupId = "courier-group")
    public void orderListener(ConsumerRecord<String, Order> record) {
        Order prepOrder = orderService.updateStatus(record.value(), Status.PREPARING);
        kafkaMessageService.send("notification-topic", prepOrder);
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Order order = orderService.updateStatus(record.value(), Status.DONE);
        kafkaMessageService.send("notification-topic", prepOrder);
    }
}