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
    public RestaurantDTO addRestaurant(RestaurantDTO requestBody) {
        RestaurantDTO restaurantDTO = null;
        try {
            var restaurant = convertToEntity(requestBody);
            restaurantDTO = convertToDto(restaurantService.addRestaurant(restaurant));
        } catch (ParseException ex) {
            log.error(ex.getMessage());
            throw new RuntimeException(ex);
        }
        return restaurantDTO;
    }

    public RestaurantDTO getRestaurantById(Long id) {
        return convertToDto(restaurantService.getRestaurantById(id));
    }

    public List<RestaurantDTO> getRestaurantsFiltered(String partialFilter) {
        return restaurantService.getRestaurantFiltered(partialFilter).stream().map(this::convertToDto).toList();
    }

    private RestaurantDTO convertToDto(Restaurant restaurant) {
        return modelMapper.map(restaurant, RestaurantDTO.class);
    }

    private Restaurant convertToEntity(RestaurantDTO restaurantDTO) throws ParseException {
        return modelMapper.map(restaurantDTO, Restaurant.class);
    }
}
