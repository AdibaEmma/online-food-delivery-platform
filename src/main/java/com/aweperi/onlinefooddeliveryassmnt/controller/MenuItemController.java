package com.aweperi.onlinefooddeliveryassmnt.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/menu-items")
@RequiredArgsConstructor
@Validated
public class MenuItemController {
    private final MenuItemServiceFacade menuItemService;

    @GetMapping
    public ResponseEntity<?> getMenuItemsByRestaurant(@RequestParam Long restaurantId) {
        return ResponseHandler.handleResponseBody(
                HttpStatus.OK,
                "Returned menu items belonging to restaurant",
                menuItemService.findMenuItemByRestaurant(restaurantId)
        );
    }
}
