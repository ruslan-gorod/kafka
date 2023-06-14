package com.epam.service;

import com.epam.entity.Order;
import com.epam.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderService {

    @Autowired
    private OrderRepository repository;

    public Order add(Order order) {
        return repository.save(order);
    }

    public Order getOrderById(long id) {
        return repository.getOrderById(id);
    }

    public Order update(Order order) {
        return repository.save(order);
    }
}