package com.aweperi.onlinefooddeliveryassmnt.service;

import com.aweperi.onlinefooddeliveryassmnt.exceptions.RestaurantNotFoundException;
import com.aweperi.onlinefooddeliveryassmnt.model.Restaurant;
import com.aweperi.onlinefooddeliveryassmnt.repository.RestaurantRepository;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@SpringBootTest
public class RestaurantServiceTest {

    @Mock
    private RestaurantRepository restaurantRepository;

    @InjectMocks
    private RestaurantService restaurantService;

    @Test
    public void testGetAllRestaurants_ReturnsPageOfRestaurants() {
        int page = 0;
        int size = 10;
        String sortBy = "name";
        String sortOrder = "asc";

        List<Restaurant> restaurantList = new ArrayList<>();
        restaurantList.add(new Restaurant());
        restaurantList.add(new Restaurant());

        Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.ASC, sortBy));
        Page<Restaurant> restaurantPage = new PageImpl<>(restaurantList, pageable, restaurantList.size());

        when(restaurantRepository.findAll(pageable)).thenReturn(restaurantPage);

        Page<Restaurant> result = restaurantService.getAllRestaurants(page, size, sortBy, sortOrder);

        assertNotNull(result);
        assertEquals(restaurantList.size(), result.getContent().size());
    }

    @Test
    public void testGetRestaurantById_ExistingRestaurantId_ReturnsRestaurant() {
        Long restaurantId = 1L;
        Restaurant restaurant = new Restaurant();
        when(restaurantRepository.findById(restaurantId)).thenReturn(Optional.of(restaurant));

        Restaurant foundRestaurant = restaurantService.getRestaurantById(restaurantId);

        assertNotNull(foundRestaurant);
        assertEquals(restaurant, foundRestaurant);
    }

    @Test
    public void testGetRestaurantById_NonExistingRestaurantId_ThrowsRestaurantNotFoundException() {
        Long restaurantId = 1L;
        when(restaurantRepository.findById(restaurantId)).thenReturn(Optional.empty());

        assertThrows(RestaurantNotFoundException.class, () -> restaurantService.getRestaurantById(restaurantId));
    }
}