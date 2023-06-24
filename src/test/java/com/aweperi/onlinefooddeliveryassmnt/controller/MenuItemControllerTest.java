package com.aweperi.onlinefooddeliveryassmnt.controller;

import com.aweperi.onlinefooddeliveryassmnt.model.MenuItem;
import com.aweperi.onlinefooddeliveryassmnt.model.Restaurant;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@RunWith(MockitoJUnitRunner.class)
public class MenuItemControllerTest {

    @Mock
    private MenuItemServiceFacade menuItemService;

    @InjectMocks
    private MenuItemController menuItemController;

    @Test
    public void testGetMenuItemsByRestaurant_ValidRestaurantId_ReturnsMenuItems() {
        // Arrange
        Long restaurantId = 1L;
        List<MenuItem> menuItems = new ArrayList<>();
        menuItems.add(new MenuItem(1L, "Burger", new BigDecimal("9.99"), new Restaurant()));
        menuItems.add(new MenuItem(2L, "Pizza", new BigDecimal("12.99"), new Restaurant()));
        Mockito.when(menuItemService.findMenuItemByRestaurant(restaurantId)).thenReturn(menuItems);

        // Act
        ResponseEntity<?> response = menuItemController.getMenuItemsByRestaurant(restaurantId);

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
    }
}