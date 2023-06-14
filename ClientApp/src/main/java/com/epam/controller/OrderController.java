package com.epam.controller;

import com.epam.entity.Order;
import com.epam.service.KafkaMessageService;
import com.epam.service.OrderService;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/pizza")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @Autowired
    private KafkaProducer<String, Order> kafkaProducer;

    @Autowired
    private KafkaMessageService kafkaMessageService;

    @PostMapping("/order")
    public Order addOrder(@RequestBody Order order) {
        Order newOrder = orderService.add(order);
        kafkaMessageService.send("order-topic", newOrder);
        return newOrder;
    }

    @GetMapping("/order/{id}")
    public Order getOrderById(@PathVariable long id) {
        return orderService.getOrderById(id);
    }

}