package com.aweperi.onlinefooddeliveryassmnt.service;

import com.aweperi.onlinefooddeliveryassmnt.model.MenuItem;
import com.aweperi.onlinefooddeliveryassmnt.model.Order;
import com.aweperi.onlinefooddeliveryassmnt.model.User;

import java.util.List;

public interface IOrderService {
    Order createOrder(Long userId, Long menuItemId, int quantity);
    List<Order> getOrdersByUser(User user);
}
