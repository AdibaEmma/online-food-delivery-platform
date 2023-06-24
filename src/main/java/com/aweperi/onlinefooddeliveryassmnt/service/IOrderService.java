package com.aweperi.onlinefooddeliveryassmnt.service;

import com.aweperi.onlinefooddeliveryassmnt.dto.OrderRequest;
import com.aweperi.onlinefooddeliveryassmnt.model.Order;

import java.util.List;

public interface IOrderService {
    Order createOrder(OrderRequest request);
    List<Order> getOrdersByUser(Long userId);

    String updateOrderStatus(Long restaurantId, Long orderId, String orderStatus);
}
