package com.aweperi.onlinefooddeliveryassmnt.dto;

import com.aweperi.onlinefooddeliveryassmnt.model.OrderStatus;
import org.springframework.lang.Nullable;

import java.time.LocalDateTime;

public class OrderDTO {
    private Long orderId;
    private UserDTO user;
    private MenuItemDTO menuItem;
    private int quantity;

    @Nullable
    private OrderStatus orderStatus;

    private LocalDateTime orderDate;
}
