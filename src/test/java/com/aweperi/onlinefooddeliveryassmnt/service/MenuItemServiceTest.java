package com.aweperi.onlinefooddeliveryassmnt.service;

import com.aweperi.onlinefooddeliveryassmnt.model.MenuItem;
import com.aweperi.onlinefooddeliveryassmnt.model.Restaurant;
import com.aweperi.onlinefooddeliveryassmnt.repository.MenuItemRepository;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

@SpringBootTest
public class MenuItemServiceTest {

    @Mock
    private RestaurantService restaurantService;

    @Mock
    private MenuItemRepository menuItemRepository;

    @InjectMocks
    private MenuItemService menuItemService;

    @Test
    public void testAddMenuItem_ValidData_ReturnsAddedMenuItem() {
        Long restaurantId = 1L;
        MenuItem menuItem = new MenuItem();

        Restaurant restaurant = new Restaurant();
        when(restaurantService.getRestaurantById(restaurantId)).thenReturn(restaurant);
        when(menuItemRepository.save(Mockito.any(MenuItem.class))).thenReturn(menuItem);

        MenuItem addedMenuItem = menuItemService.addMenuItem(restaurantId, menuItem);

        assertNotNull(addedMenuItem);
        assertEquals(restaurant, addedMenuItem.getRestaurant());
    }

    @Test
    public void testFindMenuItemByRestaurant_ValidRestaurantId_ReturnsListOfMenuItems() {
        Long restaurantId = 1L;
        Restaurant restaurant = new Restaurant();
        List<MenuItem> menuItems = new ArrayList<>();
        when(restaurantService.getRestaurantById(restaurantId)).thenReturn(restaurant);
        when(menuItemRepository.findByRestaurant(restaurant)).thenReturn(menuItems);

        List<MenuItem> foundMenuItems = menuItemService.findMenuItemByRestaurant(restaurantId);

        assertNotNull(foundMenuItems);
    }

    @Test
    public void testGetMenuItemById_ValidMenuItemId_ReturnsMenuItem() {
        Long menuItemId = 1L;
        MenuItem menuItem = new MenuItem();
        when(menuItemRepository.findById(menuItemId)).thenReturn(Optional.of(menuItem));

        MenuItem foundMenuItem = menuItemService.getMenuItemById(menuItemId);

        assertNotNull(foundMenuItem);
    }
}