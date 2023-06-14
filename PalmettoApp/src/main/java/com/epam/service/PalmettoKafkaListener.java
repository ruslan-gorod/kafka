package com.epam.service;


import com.epam.entity.Order;
import com.epam.entity.Status;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class PalmettoKafkaListener {

    @Autowired
    private PalmettoService palmettoService;

    @Autowired
    private KafkaMessageService kafkaMessageService;

    @KafkaListener(topics = "order-topic", groupId = "palmetto-group")
    public void orderListener1(ConsumerRecord<String, Order> record) {
        System.out.println("listener1");
        processOrder(record);
    }

    @KafkaListener(topics = "orderTopic", groupId = "palmettoGroup")
    public void orderListener2(ConsumerRecord<String, Order> record) {
        System.out.println("listener2");
        processOrder(record);
    }

    @KafkaListener(topics = "orderTopic", groupId = "palmettoGroup")
    public void orderListener3(ConsumerRecord<String, Order> record) {
        System.out.println("listener3");
        processOrder(record);
    }

    private void processOrder(ConsumerRecord<String, Order> record) {
        Order newOrder = palmettoService.updateOrderStatus(record.value(), Status.CREATED);
        kafkaMessageService.send("notification-topic", newOrder);

        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        Order order = palmettoService.updateOrderStatus(record.value(), Status.IN_PROGRESS);
        kafkaMessageService.send("notification-topic", order);
    }
}