package com.aweperi.onlinefooddeliveryassmnt.config;

import com.aweperi.onlinefooddeliveryassmnt.dto.MenuItemDTO;
import com.aweperi.onlinefooddeliveryassmnt.dto.RestaurantDTO;
import com.aweperi.onlinefooddeliveryassmnt.model.MenuItem;
import com.aweperi.onlinefooddeliveryassmnt.model.Restaurant;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfiguration {
    @Bean
    public ModelMapper modelMapper() {
        ModelMapper modelMapper = new ModelMapper();
        modelMapper.createTypeMap(MenuItem.class, MenuItemDTO.class)
                .addMapping(MenuItem::getRestaurant, MenuItemDTO::setRestaurantDTO);
        return modelMapper;
    }
}
