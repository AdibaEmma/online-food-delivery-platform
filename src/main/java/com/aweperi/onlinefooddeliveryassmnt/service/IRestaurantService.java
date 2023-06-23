package com.aweperi.onlinefooddeliveryassmnt.service;

import com.aweperi.onlinefooddeliveryassmnt.model.Restaurant;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface IRestaurantService {
    Page<Restaurant> getAllRestaurants(int page, int size, String sortBy, String sortOrder);
    Restaurant getRestaurantFiltered(String filter);
}
