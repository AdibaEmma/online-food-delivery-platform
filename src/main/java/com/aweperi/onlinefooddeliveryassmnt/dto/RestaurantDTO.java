package com.aweperi.onlinefooddeliveryassmnt.dto;

import jakarta.validation.constraints.NotBlank;
import org.springframework.lang.Nullable;

public class RestaurantDTO {
    @Nullable
    private Long id;
    @NotBlank
    private String name;
    @NotBlank
    private String address;
}
