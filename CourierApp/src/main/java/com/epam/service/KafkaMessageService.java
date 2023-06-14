package com.epam.service;

import com.epam.entity.Order;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class KafkaMessageService {

    @Autowired
    private KafkaProducer<String, Order> kafkaProducer;

    @Autowired
    private KafkaConsumer<String, Order> kafkaConsumer;

    public void send(String topicName, Order order) {
        ProducerRecord<String, Order> record = new ProducerRecord<>(topicName, order);
        kafkaProducer.send(record);
    }
}