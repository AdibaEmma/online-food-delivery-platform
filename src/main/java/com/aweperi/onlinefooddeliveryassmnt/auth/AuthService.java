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
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;
    public AuthenticationResponse registerUser(RegisterRequest registerDetails) {
        var userAlreadyExists = userRepository.existsByEmail(registerDetails.getEmail());
        if (userAlreadyExists) throw new UserAlreadyExistsException();

        var user = User.builder()
                .firstname(registerDetails.getFirstname())
                .lastname(registerDetails.getLastname())
                .email(registerDetails.getEmail())
                .password(passwordEncoder.encode(registerDetails.getPassword()))
                .role(Role.USER)
                .build();

        userRepository.save(user);
        var jwtToken = jwtService.generateToken(user);
        return AuthenticationResponse.builder()
                .token(jwtToken)
                .build();
    }

    public AuthenticationResponse login(AuthenticationRequest loginDetails) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginDetails.getEmail(),
                        loginDetails.getPassword()
                )
        );
        var user = userRepository.findByEmail(loginDetails.getEmail())
                .orElseThrow(UserNotFoundException::new);

        var jwtToken = jwtService.generateToken(user);
        return AuthenticationResponse.builder()
                .token(jwtToken)
                .build();
    }
}
