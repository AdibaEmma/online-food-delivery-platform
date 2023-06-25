package com.aweperi.onlinefooddeliveryassmnt.service;

import com.aweperi.onlinefooddeliveryassmnt.dto.OrderRequest;
import com.aweperi.onlinefooddeliveryassmnt.model.MenuItem;
import com.aweperi.onlinefooddeliveryassmnt.model.Order;
import com.aweperi.onlinefooddeliveryassmnt.model.Restaurant;
import com.aweperi.onlinefooddeliveryassmnt.model.User;
import com.aweperi.onlinefooddeliveryassmnt.repository.OrderRepository;
import com.aweperi.onlinefooddeliveryassmnt.repository.RestaurantRepository;
import com.aweperi.onlinefooddeliveryassmnt.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

@SpringBootTest
public class OrderServiceTest {

    @Mock
    private UserService userService;

    @Mock
    private MenuItemService menuItemService;

    @Mock
    private OrderRepository orderRepository;

    @Mock
    private UserRepository userRepository;

    @Mock
    private RestaurantRepository restaurantRepository;

    @InjectMocks
    private OrderService orderService;

    @Test
    public void testCreateOrder_ValidOrderRequest_ReturnsCreatedOrder() {
        OrderRequest request = new OrderRequest();
        when(userService.getUserById(Mockito.anyLong())).thenReturn(new User());
        when(menuItemService.getMenuItemById(Mockito.anyLong())).thenReturn(new MenuItem());
        when(orderRepository.save(Mockito.any(Order.class))).thenReturn(new Order());

        Order createdOrder = orderService.createOrder(request);
        assertNotNull(createdOrder);
    }

    @Test
    public void testGetOrdersByUser_ValidUserId_ReturnsListOfOrders() {
        Long userId = 1L;
        User user = new User();
        user.setOrders(new ArrayList<>());
        when(userService.getUserById(userId)).thenReturn(user);

        List<Order> orders = orderService.getOrdersByUser(userId);

        assertNotNull(orders);
    }

    @Test
    public void testUpdateOrderStatus_ValidData_ReturnsSuccessMessage() {
        Long restaurantId = 1L;
        Long orderId = 1L;
        String orderStatus = "COMPLETE";
        Restaurant restaurant = new Restaurant();
        Order order = new Order();
        when(restaurantRepository.findById(restaurantId)).thenReturn(Optional.of(restaurant));
        when(orderRepository.findById(orderId)).thenReturn(Optional.of(order));
        when(orderRepository.save(Mockito.any(Order.class))).thenReturn(order);

        String result = orderService.updateOrderStatus(restaurantId, orderId, orderStatus);

        assertEquals("Order status updated", result);
    }
}