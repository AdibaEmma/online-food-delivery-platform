package com.aweperi.onlinefooddeliveryassmnt.service;

import com.aweperi.onlinefooddeliveryassmnt.model.MenuItem;
import com.aweperi.onlinefooddeliveryassmnt.repository.MenuItemRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class MenuItemService implements IMenuItemService{
    private final RestaurantService restaurantService;
    private final MenuItemRepository menuItemRepository;

    @Override
    public MenuItem addMenuItem(Long restaurantId, MenuItem menuItem) {
        var foundRestaurant = restaurantService.getRestaurantById(restaurantId);
        menuItem.setRestaurant(foundRestaurant);
        return menuItemRepository.save(menuItem);
    }

    @Override
    public List<MenuItem> findMenuItemByRestaurant(Long restaurantId) {
        var foundRestaurant = restaurantService.getRestaurantById(restaurantId);
        List<MenuItem> menuItems = menuItemRepository.findByRestaurant(foundRestaurant);
        log.info("Menu Items: " + menuItems);
        return menuItems;
    }
}
