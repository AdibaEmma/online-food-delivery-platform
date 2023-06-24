package com.aweperi.onlinefooddeliveryassmnt.auth;

import com.aweperi.onlinefooddeliveryassmnt.auth.dto.AuthenticationRequest;
import com.aweperi.onlinefooddeliveryassmnt.auth.dto.RegisterRequest;
import com.aweperi.onlinefooddeliveryassmnt.controller.ResponseHandler;
import com.aweperi.onlinefooddeliveryassmnt.dto.UserDTO;
import com.aweperi.onlinefooddeliveryassmnt.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
@Validated
public class AuthController {
    private final UserService userService;

    @PostMapping("/signup")
    public ResponseEntity<?> registerUser(@Valid @RequestBody RegisterRequest registerRequest) {
        return ResponseHandler.handleResponseBody(
                HttpStatus.CREATED,
                "User registration successful",
                userService.registerUser(registerRequest)
        );
    }

    @PostMapping("/login")
    public ResponseEntity<?> loginUser(@Valid @RequestBody AuthenticationRequest loginRequest) {
        return ResponseHandler.handleResponseBody(
                HttpStatus.ACCEPTED,
                "User login successful",
                userService.loginUser(loginRequest)
        );
    }
}
