package com.aweperi.onlinefooddeliveryassmnt.controller;

import com.aweperi.onlinefooddeliveryassmnt.dto.RestaurantDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/restaurants")
@RequiredArgsConstructor
public class RestaurantController {
    private final RestaurantServiceFacade restaurantService;

    @PostMapping()
    public ResponseEntity<?> createRestaurant(@RequestBody RestaurantDTO requestBody) {
        return ResponseHandler.handleResponseBody(
                    HttpStatus.CREATED,
                    "New restaurant added",
                    restaurantService.addRestaurant(requestBody)
                );
    }
    @GetMapping()
    public ResponseEntity<?> getAllRestaurants(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "name") String sortBy,
            @RequestParam(defaultValue = "asc") String sortOrder
    ) {
        return ResponseHandler.handleResponseBody(HttpStatus.OK,
                "Fetched Restaurants Paginated",
                restaurantService.getAllRestaurants(page, size, sortBy, sortOrder)
        );
    }
}
