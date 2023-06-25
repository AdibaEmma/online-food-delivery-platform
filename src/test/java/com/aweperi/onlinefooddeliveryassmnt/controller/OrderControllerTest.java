package com.aweperi.onlinefooddeliveryassmnt.controller;

import com.aweperi.onlinefooddeliveryassmnt.dto.OrderDTO;
import com.aweperi.onlinefooddeliveryassmnt.dto.OrderRequest;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;
import java.util.ArrayList;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(OrderController.class)
public class OrderControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private OrderServiceFacade orderService;

    @Test
    public void testAddOrder_ValidOrderRequest_ReturnsCreated() throws Exception {
        OrderRequest orderRequest = new OrderRequest();
        OrderDTO order = new OrderDTO();

        Mockito.when(orderService.createOrder(orderRequest)).thenReturn(order);

        mockMvc.perform(post("/api/v1/orders")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(orderRequest)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.data").exists())
                .andExpect(jsonPath("$.message").value("New order added"));
    }

    @Test
    public void testFindOrdersByUser_ValidUserId_ReturnsOk() throws Exception {
        Long userId = 1L;
        List<OrderDTO> orders = new ArrayList<>();

        Mockito.when(orderService.getOrdersByUser(userId)).thenReturn(orders);

        mockMvc.perform(get("/api/v1/orders")
                        .param("user", String.valueOf(userId)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.data").exists())
                .andExpect(jsonPath("$.message")
                        .value("Fetched orders related by user"));
    }

    // Helper method to convert an object to JSON string
    private String asJsonString(Object object) {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            return objectMapper.writeValueAsString(object);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}