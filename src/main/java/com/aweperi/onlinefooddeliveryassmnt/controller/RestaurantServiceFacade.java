package com.aweperi.onlinefooddeliveryassmnt.controller;

import com.aweperi.onlinefooddeliveryassmnt.dto.RestaurantDTO;
import com.aweperi.onlinefooddeliveryassmnt.model.Restaurant;
import com.aweperi.onlinefooddeliveryassmnt.service.IRestaurantService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

import java.text.ParseException;
import java.util.List;

@Component
@RequiredArgsConstructor
@Slf4j
public class RestaurantServiceFacade {
    private final IRestaurantService restaurantService;

    @Autowired
    private ModelMapper modelMapper;

    Page<RestaurantDTO> getAllRestaurants(int page, int size, String sortBy, String sortOrder) {
        return restaurantService.getAllRestaurants(page, size, sortBy, sortOrder).map(this::convertToDto);
    }
    private RestaurantDTO convertToDto(Restaurant post) {
        return modelMapper.map(post, RestaurantDTO.class);
    }

    private Restaurant convertToEntity(RestaurantDTO countryDto) throws ParseException {
        return modelMapper.map(countryDto, Restaurant.class);
    }
}
