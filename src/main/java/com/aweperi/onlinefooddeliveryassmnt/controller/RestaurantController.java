package com.aweperi.onlinefooddeliveryassmnt.controller;

import com.aweperi.onlinefooddeliveryassmnt.dto.MenuItemDTO;
import com.aweperi.onlinefooddeliveryassmnt.dto.RestaurantDTO;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/restaurants")
@RequiredArgsConstructor
@Validated
public class RestaurantController {
    private final RestaurantServiceFacade restaurantService;
    private final MenuItemServiceFacade menuItemService;

    @PostMapping()
    public ResponseEntity<?> createRestaurant(@Valid @RequestBody RestaurantDTO requestBody) {
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

    @GetMapping("/{id}")
    public ResponseEntity<?> getRestaurantById(@PathVariable("id") Long id) {
        return ResponseHandler.handleResponseBody(
                HttpStatus.OK,
                "Returned restaurant by id",
                restaurantService.getRestaurantById(id)
        );
    }

    @GetMapping("/filter")
    public ResponseEntity<?> findRestaurantsFiltered(@RequestParam("partialFilter") String partialFilter) {
        return ResponseHandler.handleResponseBody(
                HttpStatus.OK,
                "Query return restaurants results",
                restaurantService.getRestaurantsFiltered(partialFilter)
        );
    }

    @PostMapping("/{restaurantId}/menu-items")
    public ResponseEntity<?> createMenuItem(@PathVariable("restaurantId") Long restaurantId, @Valid @RequestBody MenuItemDTO requestBody) {
        return ResponseHandler.handleResponseBody(
                HttpStatus.CREATED,
                "New menu item added",
                menuItemService.addMenuItem(restaurantId, requestBody)
        );
    }
}
