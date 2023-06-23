package com.aweperi.onlinefooddeliveryassmnt.controller;

import com.aweperi.onlinefooddeliveryassmnt.dto.MenuItemDTO;
import com.aweperi.onlinefooddeliveryassmnt.dto.RestaurantDTO;
import com.aweperi.onlinefooddeliveryassmnt.model.MenuItem;
import com.aweperi.onlinefooddeliveryassmnt.model.Restaurant;
import com.aweperi.onlinefooddeliveryassmnt.service.IMenuItemService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.text.ParseException;

@Component
@RequiredArgsConstructor
@Slf4j
public class MenuItemServiceFacade {
    private final IMenuItemService menuItemService;

    @Autowired
    private ModelMapper modelMapper;

    private MenuItemDTO convertToDto(MenuItem menuItem) {
        return modelMapper.map(menuItem, MenuItemDTO.class);
    }

    private MenuItem convertToEntity(MenuItemDTO menuItemDTO) throws ParseException {
        return modelMapper.map(menuItemDTO, MenuItem.class);
    }
}
