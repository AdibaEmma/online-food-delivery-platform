package com.aweperi.onlinefooddeliveryassmnt.controller;

import com.aweperi.onlinefooddeliveryassmnt.dto.OrderDTO;
import com.aweperi.onlinefooddeliveryassmnt.model.Order;
import com.aweperi.onlinefooddeliveryassmnt.service.IOrderService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.text.ParseException;
import java.util.List;

@Component
@RequiredArgsConstructor
@Slf4j
public class OrderServiceFacade {
    private final IOrderService orderService;

    @Autowired
    private ModelMapper modelMapper;

    public OrderDTO createOrder(OrderDTO request) throws ParseException {
        OrderDTO orderDTO;
        try {
             var order = convertToEntity(request);
             orderDTO = convertToDto(orderService.createOrder(order));
        }catch (ParseException ex) {
            log.error(ex.getMessage());
            throw new RuntimeException(ex);
        }
        return orderDTO;
    }

    public List<OrderDTO> getOrdersByUser(Long userId) {
        return orderService.getOrdersByUser(userId).stream().map(this::convertToDto).toList();
    }

    private OrderDTO convertToDto(Order order) {
        return modelMapper.map(order, OrderDTO.class);
    }

    private Order convertToEntity(OrderDTO orderDTO) throws ParseException {
        return modelMapper.map(orderDTO, Order.class);
    }
}
