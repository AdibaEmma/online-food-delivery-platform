package com.aweperi.onlinefooddeliveryassmnt.repository;

import com.aweperi.onlinefooddeliveryassmnt.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
}
