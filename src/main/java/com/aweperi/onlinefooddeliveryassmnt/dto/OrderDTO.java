package com.aweperi.onlinefooddeliveryassmnt.dto;

import com.aweperi.onlinefooddeliveryassmnt.model.OrderStatus;

import java.time.LocalDateTime;

public class OrderDTO {
    private Long orderId;
    private UserDTO user;
    private MenuItemDTO menuItem;
    private int quantity;
    private OrderStatus orderStatus;

    private LocalDateTime orderDate;
}
