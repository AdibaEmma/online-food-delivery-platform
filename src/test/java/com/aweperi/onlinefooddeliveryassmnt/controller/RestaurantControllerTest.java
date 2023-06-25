package com.aweperi.onlinefooddeliveryassmnt.controller;

import com.aweperi.onlinefooddeliveryassmnt.dto.MenuItemDTO;
import com.aweperi.onlinefooddeliveryassmnt.dto.RestaurantDTO;
import com.aweperi.onlinefooddeliveryassmnt.service.IOrderService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Collections;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(RestaurantController.class)
public class RestaurantControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private RestaurantServiceFacade restaurantService;

    @MockBean
    private MenuItemServiceFacade menuItemService;

    @MockBean
    private IOrderService orderService;

    @Test
    public void testCreateRestaurant_ReturnsCreated() throws Exception {
        RestaurantDTO restaurantDTO = new RestaurantDTO();

        Mockito.when(restaurantService.addRestaurant(any(RestaurantDTO.class)))
                .thenReturn(restaurantDTO);

        mockMvc.perform(post("/api/v1/restaurants")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"name\":\"Restaurant Name\",\"address\":\"Restaurant Address\"}"))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.data").exists())
                .andExpect(jsonPath("$.message").value("New restaurant added"));
    }

    @Test
    public void testGetAllRestaurants_ReturnsOk() throws Exception {
        int page = 0;
        int size = 10;
        String sortBy = "name";
        String sortOrder = "asc";
        Page<RestaurantDTO> restaurantPage = new PageImpl<>(Collections.emptyList());

        Mockito.when(restaurantService.getAllRestaurants(page, size, sortBy, sortOrder))
                .thenReturn(restaurantPage);

        mockMvc.perform(get("/api/v1/restaurants"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.data").exists())
                .andExpect(jsonPath("$.message")
                        .value("Fetched Restaurants Paginated"));
    }

    @Test
    public void testGetRestaurantById_ExistingRestaurantId_ReturnsOk() throws Exception {
        Long restaurantId = 1L;
        RestaurantDTO restaurant = new RestaurantDTO();

        Mockito.when(restaurantService.getRestaurantById(restaurantId)).thenReturn(restaurant);

        mockMvc.perform(get("/api/v1/restaurants/{id}", restaurantId))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.data").exists())
                .andExpect(jsonPath("$.message")
                        .value("Returned restaurant by id"));
    }

    @Test
    public void testFindRestaurantsFiltered_ReturnsOk() throws Exception {
        String partialFilter = "filter";
        List<RestaurantDTO> filteredRestaurants = Collections.singletonList(new RestaurantDTO());

        Mockito.when(restaurantService.getRestaurantsFiltered(partialFilter))
                .thenReturn(filteredRestaurants);

        mockMvc.perform(get("/api/v1/restaurants/filter")
                        .param("partialFilter", partialFilter))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.data").exists())
                .andExpect(jsonPath("$.message")
                        .value("Query return restaurants results"));
    }

    @Test
    public void testCreateMenuItem_ReturnsCreated() throws Exception {
        // Arrange
        Long restaurantId = 1L;
        MenuItemDTO menuItemDTO = new MenuItemDTO();

        Mockito.when(menuItemService.addMenuItem(eq(restaurantId),
                any(MenuItemDTO.class))).thenReturn(menuItemDTO);

        mockMvc.perform(post("/api/v1/restaurants/{restaurantId}/menu-items", restaurantId)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"name\":\"Menu Item Name\",\"price\":9.99}"))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.data").exists())
                .andExpect(jsonPath("$.message").value("New menu item added"));
    }

    @Test
    public void testUpdateOrderStatus_ReturnsAccepted() throws Exception {
        Long restaurantId = 1L;
        Long orderId = 2L;
        String orderStatus = "DELIVERED";

        Mockito.when(orderService.updateOrderStatus(restaurantId, orderId, orderStatus)).thenReturn("Order status updated");

        mockMvc.perform(put("/api/v1/restaurants/{restaurantId}/orders/{orderId}/order-fulfilment", restaurantId, orderId)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("\"DELIVERED\""))
                .andExpect(status().isAccepted())
                .andExpect(jsonPath("$.data").exists())
                .andExpect(jsonPath("$.message").value("Order status changed"));
    }
}