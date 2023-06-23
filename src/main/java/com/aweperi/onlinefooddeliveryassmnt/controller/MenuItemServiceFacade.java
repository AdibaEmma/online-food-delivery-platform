package com.aweperi.onlinefooddeliveryassmnt.controller;

import com.aweperi.onlinefooddeliveryassmnt.dto.MenuItemDTO;
import com.aweperi.onlinefooddeliveryassmnt.model.MenuItem;
import com.aweperi.onlinefooddeliveryassmnt.service.IMenuItemService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.text.ParseException;
import java.util.List;

@Component
@RequiredArgsConstructor
@Slf4j
public class MenuItemServiceFacade {
    private final IMenuItemService menuItemService;

    @Autowired
    private ModelMapper modelMapper;

    public MenuItemDTO addMenuItem(Long restaurantId, MenuItemDTO requestBody) {
        MenuItemDTO  menuItemDTO;
        try {
            MenuItem newMenuItem = convertToEntity(requestBody);
            menuItemDTO = convertToDto(menuItemService.addMenuItem(restaurantId, newMenuItem));
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
        return menuItemDTO;
    }

    public List<MenuItemDTO> findMenuItemByRestaurant(Long restaurantId) {
        return menuItemService.findMenuItemByRestaurant(restaurantId).stream().map(this::convertToDto).toList();
    }

    private MenuItemDTO convertToDto(MenuItem menuItem) {
        return modelMapper.map(menuItem, MenuItemDTO.class);
    }

    private MenuItem convertToEntity(MenuItemDTO menuItemDTO) throws ParseException {
        return modelMapper.map(menuItemDTO, MenuItem.class);
    }

}
