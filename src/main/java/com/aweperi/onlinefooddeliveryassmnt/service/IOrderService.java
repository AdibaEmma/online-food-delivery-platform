package com.aweperi.onlinefooddeliveryassmnt.service;

import com.aweperi.onlinefooddeliveryassmnt.model.Order;

import java.util.List;

public interface IOrderService {
    Order createOrder(Order order);
    List<Order> getOrdersByUser(Long userId);
}
