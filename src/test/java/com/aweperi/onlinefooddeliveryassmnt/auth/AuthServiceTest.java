package com.aweperi.onlinefooddeliveryassmnt.auth;

import com.aweperi.onlinefooddeliveryassmnt.auth.dto.AuthenticationRequest;
import com.aweperi.onlinefooddeliveryassmnt.auth.dto.AuthenticationResponse;
import com.aweperi.onlinefooddeliveryassmnt.auth.dto.RegisterRequest;
import com.aweperi.onlinefooddeliveryassmnt.exceptions.UserAlreadyExistsException;
import com.aweperi.onlinefooddeliveryassmnt.exceptions.UserNotFoundException;
import com.aweperi.onlinefooddeliveryassmnt.model.Role;
import com.aweperi.onlinefooddeliveryassmnt.model.User;
import com.aweperi.onlinefooddeliveryassmnt.repository.UserRepository;
import com.aweperi.onlinefooddeliveryassmnt.service.JwtService;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.ArrayList;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(MockitoJUnitRunner.class)
public class AuthServiceTest {
    @Mock
    private UserRepository userRepository;
    @Mock
    private PasswordEncoder passwordEncoder;
    @Mock
    private JwtService jwtService;
    @Mock
    private AuthenticationManager authenticationManager;

    @InjectMocks
    private AuthService authService;

    @Test
    public void testRegisterUser_ValidRegisterRequest_ReturnsAuthenticationResponse() {
        // Arrange
        RegisterRequest registerRequest = new RegisterRequest("John", "Doe", "john@example.com", "password123");
        User registeredUser = new User(
                1L,
                "John",
                "Doe",
                "john@example.com",
                "TestPassword",
                Role.USER,
                new ArrayList<>()
        );
        String jwtToken = "generated_token";
        Mockito.when(userRepository.existsByEmail(registerRequest.getEmail())).thenReturn(false);
        Mockito.when(passwordEncoder.encode(registerRequest.getPassword())).thenReturn("encoded_password");
        Mockito.when(userRepository.save(Mockito.any(User.class))).thenReturn(registeredUser);
        Mockito.when(jwtService.generateToken(registeredUser)).thenReturn(jwtToken);

        AuthenticationResponse response = authService.registerUser(registerRequest);

        assertNotNull(response);
        assertEquals(jwtToken, response.getToken());
        // Add more assertions to validate the response
    }

    @Test
    public void testRegisterUser_UserAlreadyExists_ThrowsUserAlreadyExistsException() {
        RegisterRequest registerRequest = new RegisterRequest("John", "Doe",
                "john@example.com", "password123");
        Mockito.when(userRepository.existsByEmail(registerRequest.getEmail())).thenReturn(true);

        assertThrows(UserAlreadyExistsException.class, () -> authService.registerUser(registerRequest));

    }

    @Test
    public void testLogin_ValidAuthenticationRequest_ReturnsAuthenticationResponse() {
        // Arrange
        AuthenticationRequest loginRequest = new AuthenticationRequest("john@example.com", "password123");
        User foundUser = new User(
                1L,
                "John",
                "Doe",
                "john@example.com",
                "TestPassword",
                Role.USER,
                new ArrayList<>()
        );
        String jwtToken = "generated_token";
        Mockito.when(authenticationManager.authenticate(Mockito.any(Authentication.class))).thenReturn(null);
        Mockito.when(userRepository.findByEmail(loginRequest.getEmail())).thenReturn(Optional.of(foundUser));
        Mockito.when(jwtService.generateToken(foundUser)).thenReturn(jwtToken);

        // Act
        AuthenticationResponse response = authService.login(loginRequest);

        // Assert
        assertNotNull(response);
        assertEquals(jwtToken, response.getToken());
    }

    @Test
    public void testLogin_UserNotFound_ThrowsUserNotFoundException() {
        // Arrange
        AuthenticationRequest loginRequest = new AuthenticationRequest("john@example.com", "password123");
        Mockito.when(authenticationManager.authenticate(Mockito.any(Authentication.class))).thenReturn(null);
        Mockito.when(userRepository.findByEmail(loginRequest.getEmail())).thenReturn(Optional.empty());

        assertThrows(UserNotFoundException.class, () -> authService.login(loginRequest));
    }
}