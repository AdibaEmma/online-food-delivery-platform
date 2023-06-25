package com.aweperi.onlinefooddeliveryassmnt.controller;

import com.aweperi.onlinefooddeliveryassmnt.dto.MenuItemDTO;
import com.aweperi.onlinefooddeliveryassmnt.dto.RestaurantDTO;
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
        List<MenuItemDTO> menuItems = new ArrayList<>();
        menuItems.add(new MenuItemDTO(1L, "Burger", new BigDecimal("9.99"), new RestaurantDTO()));
        menuItems.add(new MenuItemDTO(2L, "Pizza", new BigDecimal("12.99"), new RestaurantDTO()));
        Mockito.when(menuItemService.findMenuItemByRestaurant(restaurantId)).thenReturn(menuItems);

        // Act
        ResponseEntity<?> response = menuItemController.getMenuItemsByRestaurant(restaurantId);

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
    }
}