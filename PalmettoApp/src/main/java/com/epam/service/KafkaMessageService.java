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

    public void send(String topicName, Order order) {
        kafkaProducer.send(new ProducerRecord<>(topicName, order));
    }
}