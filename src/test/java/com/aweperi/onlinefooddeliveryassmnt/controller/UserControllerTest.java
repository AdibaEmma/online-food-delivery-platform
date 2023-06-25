package com.aweperi.onlinefooddeliveryassmnt.controller;

import com.aweperi.onlinefooddeliveryassmnt.dto.UserDTO;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(UserController.class)
public class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserServiceFacade userService;

    @Test
    public void testGetUserById_ExistingUserId_ReturnsOk() throws Exception {
        Long userId = 1L;
        UserDTO user = new UserDTO();

        Mockito.when(userService.getUserById(userId)).thenReturn(user);

        mockMvc.perform(get("/api/v1/users/{id}", userId))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.data").exists())
                .andExpect(jsonPath("$.message").value("Returned user by username"));
    }
}