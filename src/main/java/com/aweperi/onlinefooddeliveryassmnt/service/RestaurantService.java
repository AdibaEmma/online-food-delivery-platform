package com.aweperi.onlinefooddeliveryassmnt.service;

import com.aweperi.onlinefooddeliveryassmnt.exceptions.RestaurantNotFoundException;
import com.aweperi.onlinefooddeliveryassmnt.model.Restaurant;
import com.aweperi.onlinefooddeliveryassmnt.repository.RestaurantRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RestaurantService implements IRestaurantService{
    private final RestaurantRepository restaurantRepository;
    @Override
    public Page<Restaurant> getAllRestaurants(int page, int size, String sortBy, String sortOrder) {
        Sort sort = Sort.by(Sort.Direction.fromString(sortOrder), sortBy);
        Pageable pageable = PageRequest.of(page, size, sort);
        Page<Restaurant> restaurantPage = restaurantRepository.findAll(pageable);
        return restaurantPage;
    }

    @Override
    public Restaurant getRestaurantById(Long id) {
        var foundRestaurant = restaurantRepository.findById(id);
        if(foundRestaurant.isEmpty())
            throw new RestaurantNotFoundException(String.format("Restaurant with id: %s", id));
        return foundRestaurant.get();
    }

    @Override
    public Restaurant getRestaurantFiltered(String filter) {
        return restaurantRepository.findByNameOrAddress(filter, filter).orElseThrow(RestaurantNotFoundException::new);
    }
}
