package com.aweperi.onlinefooddeliveryassmnt.service;

import com.aweperi.onlinefooddeliveryassmnt.exceptions.OrderNotFoundException;
import com.aweperi.onlinefooddeliveryassmnt.exceptions.RestaurantNotFoundException;
import com.aweperi.onlinefooddeliveryassmnt.model.Order;
import com.aweperi.onlinefooddeliveryassmnt.model.OrderStatus;
import com.aweperi.onlinefooddeliveryassmnt.repository.OrderRepository;
import com.aweperi.onlinefooddeliveryassmnt.repository.RestaurantRepository;
import com.aweperi.onlinefooddeliveryassmnt.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderService implements IOrderService {
    private final UserService userService;
    private final MenuItemService menuItemService;
    private final OrderRepository orderRepository;
    private final UserRepository userRepository;
    private final RestaurantRepository restaurantRepository;

    @Override
    public Order createOrder(Order order) {
        var user = userService.getUserById(order.getUser().getUserId());
        var menuItem = menuItemService.getMenuItemById(order.getMenuItem().getId());

        var newOrder = Order.builder()
                .user(user)
                .menuItem(menuItem)
                .quantity(order.getQuantity())
                .orderStatus(OrderStatus.PENDING)
                .orderDate(LocalDateTime.now())
                .build();
        orderRepository.save(newOrder);

        user.addUserOrder(newOrder);
        userRepository.save(user);

        return newOrder;
    }

    @Override
    public List<Order> getOrdersByUser(Long userId) {
        var user = userService.getUserById(userId);
        return (List<Order>) user.getOrders();
    }

    @Override
    public String updateOrderStatus(Long restaurantId, Long orderId, String orderStatus) {
        restaurantRepository.findById(restaurantId).orElseThrow(RestaurantNotFoundException::new);

        var order = orderRepository.findById(orderId).orElseThrow(OrderNotFoundException::new);

        order.setOrderStatus(OrderStatus.valueOf(orderStatus.toUpperCase()));
        orderRepository.save(order);
        return "Order status updated";
    }
}
