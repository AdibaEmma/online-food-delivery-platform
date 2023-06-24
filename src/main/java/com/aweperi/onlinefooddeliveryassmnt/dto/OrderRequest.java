package com.aweperi.onlinefooddeliveryassmnt.dto;

import com.aweperi.onlinefooddeliveryassmnt.model.OrderStatus;
import lombok.Data;

@Data
public class OrderRequest {
    private Long userId;
    private Long menuItemId;
    private int quantity;
    private OrderStatus orderStatus;
}
