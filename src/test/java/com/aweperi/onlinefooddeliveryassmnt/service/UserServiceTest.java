package com.aweperi.onlinefooddeliveryassmnt.service;

import com.aweperi.onlinefooddeliveryassmnt.exceptions.UserNotFoundException;
import com.aweperi.onlinefooddeliveryassmnt.model.User;
import com.aweperi.onlinefooddeliveryassmnt.repository.UserRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@RunWith(MockitoJUnitRunner.class)
public class UserServiceTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserService userService;

    @Test
    public void testGetUserById_ExistingUser_ReturnsUser() {
        // Arrange
        Long userId = 1L;
        User user = new User();
        user.setUserId(userId);
        Mockito.when(userRepository.findById(userId)).thenReturn(Optional.of(user));

        // Act
        User result = userService.getUserById(userId);

        // Assert
        assertEquals(userId, result.getUserId());
        // Add additional assertions for other properties if needed
        Mockito.verify(userRepository, Mockito.times(1)).findById(userId);
    }

    @Test(expected = UserNotFoundException.class)
    public void testGetUserById_NonExistingUser_ThrowsUserNotFoundException() {
        // Arrange
        Long userId = 1L;
        Mockito.when(userRepository.findById(userId)).thenReturn(Optional.empty());

        assertThrows(UserNotFoundException.class, () -> {
            userService.getUserById(userId);
        });
    }
}