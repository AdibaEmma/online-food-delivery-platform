package com.aweperi.onlinefooddeliveryassmnt.service;

import com.aweperi.onlinefooddeliveryassmnt.model.MenuItem;

import java.util.List;

public interface IMenuItemService {
    MenuItem addMenuItem(MenuItem menuItem);

    List<MenuItem> findMenuItemByRetaurant(Long restaurantId);
}
