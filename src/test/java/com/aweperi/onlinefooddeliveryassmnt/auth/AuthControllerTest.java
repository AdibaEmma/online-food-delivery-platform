package com.aweperi.onlinefooddeliveryassmnt.auth;

import com.aweperi.onlinefooddeliveryassmnt.auth.dto.AuthenticationRequest;
import com.aweperi.onlinefooddeliveryassmnt.auth.dto.AuthenticationResponse;
import com.aweperi.onlinefooddeliveryassmnt.auth.dto.RegisterRequest;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@RunWith(MockitoJUnitRunner.class)
public class AuthControllerTest {

    @Mock
    private AuthService authService;

    @InjectMocks
    private AuthController authController;

    @Test
    public void testRegisterUser_ValidRegisterRequest_ReturnsSuccessResponse() {
        // Arrange
        RegisterRequest registerRequest = new RegisterRequest(
                "John",
                "Doe",
                "john@example.com",
                "password123");

        AuthenticationResponse authenticationResponse = AuthenticationResponse.builder()
                .token("generated_token")
                .build();
        Mockito.when(authService.registerUser(registerRequest)).thenReturn(authenticationResponse);

        ResponseEntity<?> response = authController.registerUser(registerRequest);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertNotNull(response.getBody());

    }

    @Test
    public void testLoginUser_ValidAuthenticationRequest_ReturnsSuccessResponse() {
        // Arrange
        AuthenticationRequest loginRequest = new AuthenticationRequest(
                "john@example.com", "password123");

        AuthenticationResponse authenticationResponse = AuthenticationResponse.builder()
                .token("generated_token")
                .build();
        Mockito.when(authService.login(loginRequest)).thenReturn(authenticationResponse);

        ResponseEntity<?> response = authController.loginUser(loginRequest);

        assertEquals(HttpStatus.ACCEPTED, response.getStatusCode());
        assertNotNull(response.getBody());
    }

    // Add additional test methods for different scenarios, such as invalid requests, error responses, etc.
}