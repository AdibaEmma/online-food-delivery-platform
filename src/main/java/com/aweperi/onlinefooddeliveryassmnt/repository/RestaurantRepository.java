package com.aweperi.onlinefooddeliveryassmnt.repository;

import com.aweperi.onlinefooddeliveryassmnt.model.Restaurant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RestaurantRepository extends JpaRepository<Restaurant, Long> {
    Restaurant findByNameOrAddress(String name, String address);
}
