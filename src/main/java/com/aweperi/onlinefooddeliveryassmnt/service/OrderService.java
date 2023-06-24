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
    public Order createOrder(Order order) {
        var user = userService.getUserById(order.getUser().getUserId());
        var menuItem = menuItemService.getMenuItemById(order.getMenuItem().getId());

        var newOrder = Order.builder()
                .user(user)
                .menuItem(menuItem)
                .quantity(order.getQuantity())
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
}
