package com.aweperi.onlinefooddeliveryassmnt.auth;

import com.aweperi.onlinefooddeliveryassmnt.auth.dto.AuthenticationRequest;
import com.aweperi.onlinefooddeliveryassmnt.auth.dto.AuthenticationResponse;
import com.aweperi.onlinefooddeliveryassmnt.auth.dto.RegisterRequest;
import com.aweperi.onlinefooddeliveryassmnt.model.Role;
import com.aweperi.onlinefooddeliveryassmnt.model.User;
import com.aweperi.onlinefooddeliveryassmnt.repository.UserRepository;
import com.aweperi.onlinefooddeliveryassmnt.service.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    public AuthenticationResponse registerUser(RegisterRequest registerDetails) {
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

    public String login(AuthenticationRequest loginDetails) {
        return null;
    }
}
