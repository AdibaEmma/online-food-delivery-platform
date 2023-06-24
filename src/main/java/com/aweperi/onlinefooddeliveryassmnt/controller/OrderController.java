package com.aweperi.onlinefooddeliveryassmnt.controller;

import com.aweperi.onlinefooddeliveryassmnt.dto.OrderRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;

@RestController
@RequestMapping("/api/v1/orders")
@RequiredArgsConstructor
@Validated
public class OrderController {
    private final OrderServiceFacade orderService;

    @PostMapping
    public ResponseEntity<?> addOrder(@Valid @RequestBody OrderRequest request) {
        return ResponseHandler.handleResponseBody(
                HttpStatus.CREATED,
                "New order added",
                orderService.createOrder(request)
        );
    }

    @GetMapping
    public ResponseEntity<?> findOrdersByUser(@RequestParam("user") Long userId) {
        return ResponseHandler.handleResponseBody(
                HttpStatus.OK,
                "Fetched orders related by user",
                orderService.getOrdersByUser(userId)
        );
    }
}
