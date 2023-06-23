package com.aweperi.onlinefooddeliveryassmnt.dto;

import org.springframework.lang.Nullable;

import java.math.BigDecimal;
public class MenuItemDTO {
    @Nullable
    private Long id;
    private String name;
    private BigDecimal price;
}
