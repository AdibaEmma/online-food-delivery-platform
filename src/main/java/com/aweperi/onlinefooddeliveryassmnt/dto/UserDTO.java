package com.aweperi.onlinefooddeliveryassmnt.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDTO {
    @NotBlank
    private String email;
    @NotBlank
    private String password;
}
