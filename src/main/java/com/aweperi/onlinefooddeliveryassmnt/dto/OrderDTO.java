package com.aweperi.onlinefooddeliveryassmnt.dto;

import java.time.LocalDateTime;

public class OrderDTO {
    private Long id;
    private UserDTO user;
    private MenuItemDTO menuItem;
    private int quantity;
    private LocalDateTime orderDate;
}
