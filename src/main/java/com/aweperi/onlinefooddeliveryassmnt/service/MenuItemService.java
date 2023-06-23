package com.aweperi.onlinefooddeliveryassmnt.service;

import com.aweperi.onlinefooddeliveryassmnt.model.MenuItem;
import com.aweperi.onlinefooddeliveryassmnt.repository.MenuItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
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
    public List<MenuItem> findMenuItemByRetaurant(Long restaurantId) {
        var foundRestaurant = restaurantService.getRestaurantById(restaurantId);
        List<MenuItem> menuItems = menuItemRepository.findByRestaurant(foundRestaurant);
        return menuItems;
    }
}
