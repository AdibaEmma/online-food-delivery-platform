package com.aweperi.onlinefooddeliveryassmnt.dto;

import com.aweperi.onlinefooddeliveryassmnt.model.OrderStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderDTO {
    private Long orderId;
    private UserDTO user;
    private MenuItemDTO menuItem;
    private int quantity;
    private OrderStatus orderStatus;

    private LocalDateTime orderDate;
}
