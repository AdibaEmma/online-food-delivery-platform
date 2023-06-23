package com.aweperi.onlinefooddeliveryassmnt.service;

import com.aweperi.onlinefooddeliveryassmnt.model.MenuItem;
import com.aweperi.onlinefooddeliveryassmnt.model.Order;
import com.aweperi.onlinefooddeliveryassmnt.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderService implements IOrderService {
    @Override
    public Order createOrder(User user, MenuItem menuItem, int quantity) {
        return null;
    }

    @Override
    public List<Order> getOrdersByUser(User user) {
        return null;
    }
}
