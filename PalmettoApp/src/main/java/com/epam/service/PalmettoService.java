package com.epam.service;

import com.epam.entity.Order;
import com.epam.entity.Status;
import org.springframework.stereotype.Service;

@Service
public class PalmettoService {

    public Order updateOrderStatus(Order order, Status status) {
        order.setStatus(status);
        return order;
    }
}