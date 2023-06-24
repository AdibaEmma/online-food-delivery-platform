package com.aweperi.onlinefooddeliveryassmnt.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/users")
@RequiredArgsConstructor
@Validated
public class UserController {
    private final UserServiceFacade userService;

    @GetMapping("/{username}")
    public ResponseEntity<?> getUserByUsername(@PathVariable("username") String username) {
        return ResponseHandler.handleResponseBody(
                HttpStatus.OK,
                "Returned user by username",
                userService.getUserByUsername(username)
        );
    }
}
