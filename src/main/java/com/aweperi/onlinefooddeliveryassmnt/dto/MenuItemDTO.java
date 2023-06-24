package com.aweperi.onlinefooddeliveryassmnt.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.lang.Nullable;

import java.math.BigDecimal;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class MenuItemDTO {
    @Nullable
    private Long menuItemId;
    @NotBlank
    private String name;
    @Min(1)
    private BigDecimal price;

    @Nullable private RestaurantDTO restaurantDTO;
}
