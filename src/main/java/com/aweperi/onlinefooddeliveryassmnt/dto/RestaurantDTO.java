package com.aweperi.onlinefooddeliveryassmnt.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.lang.Nullable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RestaurantDTO {
    @Nullable
    private Long id;
    @NotBlank
    private String name;
    @NotBlank
    private String address;
}
