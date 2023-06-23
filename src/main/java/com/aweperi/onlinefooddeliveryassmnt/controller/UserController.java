package com.aweperi.onlinefooddeliveryassmnt.controller;

import com.aweperi.onlinefooddeliveryassmnt.dto.UserDTO;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/vi/users")
@RequiredArgsConstructor
@Validated
public class UserController {
    private final UserServiceFacade userService;

    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@Valid @RequestBody UserDTO requestBody) {
        return ResponseHandler.handleResponseBody(
                HttpStatus.CREATED,
                "User registration successful",
                userService.registerUser(requestBody)
        );
    }

    @PostMapping("/login")
    public ResponseEntity<?> loginUser(@Valid @RequestBody UserDTO userDTO) {
        return ResponseHandler.handleResponseBody(
                HttpStatus.ACCEPTED,
                "User login successful",
                userService.loginUser(userDTO)
        );
    }

    @GetMapping("/{username}")
    public ResponseEntity<? getUserByUsername(@PathVariable("username") String username {
        return ResponseHandler.handleResponseBody(
                HttpStatus.OK,
                "Returned user by username",
                userService.getUserByUsername(username)
        );
    }
}
