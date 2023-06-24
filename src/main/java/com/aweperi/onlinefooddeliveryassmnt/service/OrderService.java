package com.aweperi.onlinefooddeliveryassmnt.service;

import com.aweperi.onlinefooddeliveryassmnt.model.Order;
import com.aweperi.onlinefooddeliveryassmnt.repository.OrderRepository;
import com.aweperi.onlinefooddeliveryassmnt.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderService implements IOrderService {
    private final UserService userService;
    private final MenuItemService menuItemService;
    private final OrderRepository orderRepository;
    private final UserRepository userRepository;

    @Override
    public Order createOrder(Long userId, Long menuItemId, int quantity) {
        var user = userService.getUserById(userId);
        var menuItem = menuItemService.getMenuItemById(menuItemId);

        var order = Order.builder()
                .user(user)
                .menuItem(menuItem)
                .quantity(quantity)
                .build();
        orderRepository.save(order);

        user.addUserOrder(order);
        userRepository.save(user);

        return order;
    }

    @Override
    public List<Order> getOrdersByUser(Long userId) {
        var user = userService.getUserById(userId);
        return (List<Order>) user.getOrders();
    }
}
