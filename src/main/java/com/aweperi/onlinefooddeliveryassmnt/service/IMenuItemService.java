package com.aweperi.onlinefooddeliveryassmnt.service;

import com.aweperi.onlinefooddeliveryassmnt.model.MenuItem;

import java.util.List;

public interface IMenuItemService {
    MenuItem addMenuItem(Long restaurantId, MenuItem menuItem);

    List<MenuItem> findMenuItemByRestaurant(Long restaurantId);

    MenuItem getMenuItemById(Long menuItemId);
}
