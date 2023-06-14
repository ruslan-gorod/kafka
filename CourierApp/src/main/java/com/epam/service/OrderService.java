package com.epam.service;

import com.epam.entity.Order;
import com.epam.entity.Status;
import org.springframework.stereotype.Service;

@Service
public class OrderService {

    public Order updateStatus(Order order, Status status) {
        order.setStatus(status);
        return order;
    }
}