package com.aweperi.onlinefooddeliveryassmnt.service;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.security.core.userdetails.UserDetails;

import java.security.Key;
import io.jsonwebtoken.Claims;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;

@RunWith(MockitoJUnitRunner.class)
public class JwtServiceTest {

    @Mock
    private UserDetails userDetails;

    @InjectMocks
    private JwtService jwtService;

    @Test
    public void testExtractUsername_ValidToken_ReturnsUsername() {
        // Arrange
        String token = "valid_token";
        String username = "testuser";
        Mockito.when(jwtService.extractClaim(token, Claims::getSubject)).thenReturn(username);

        // Act
        String result = jwtService.extractUsername(token);

        // Assert
        assertEquals(username, result);
    }

    @Test
    public void testGenerateToken_ReturnsToken() {
        // Arrange
        String expectedToken = "generated_token";
        Mockito.when(jwtService.getSigningKey()).thenReturn(mock(Key.class));
        Mockito.when(userDetails.getUsername()).thenReturn("testuser");

        // Act
        String result = jwtService.generateToken(userDetails);

        // Assert
        assertNotNull(result);
        assertEquals(expectedToken, result);
    }

    @Test
    public void testIsTokenValid_ValidTokenAndUserDetails_ReturnsTrue() {
        // Arrange
        String token = "valid_token";
        String username = "testuser";
        Mockito.when(jwtService.extractUsername(token)).thenReturn(username);
        Mockito.when(userDetails.getUsername()).thenReturn(username);
        Mockito.when(jwtService.isTokenExpired(token)).thenReturn(false);

        // Act
        boolean result = jwtService.isTokenValid(token, userDetails);

        // Assert
        assertTrue(result);
    }

    @Test
    public void testIsTokenValid_ExpiredToken_ReturnsFalse() {
        // Arrange
        String token = "expired_token";
        String username = "testuser";
        Mockito.when(jwtService.extractUsername(token)).thenReturn(username);
        Mockito.when(userDetails.getUsername()).thenReturn(username);
        Mockito.when(jwtService.isTokenExpired(token)).thenReturn(true);

        // Act
        boolean result = jwtService.isTokenValid(token, userDetails);

        // Assert
        assertFalse(result);
    }
}