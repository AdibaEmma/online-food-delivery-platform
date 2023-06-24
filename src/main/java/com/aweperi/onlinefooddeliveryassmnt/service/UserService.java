package com.aweperi.onlinefooddeliveryassmnt.service;

import com.aweperi.onlinefooddeliveryassmnt.model.User;
import com.aweperi.onlinefooddeliveryassmnt.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService implements IUserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtTokenUtil jwtTokenUtil;
    @Override
    public void registerUser(User user) {

    }

    @Override
    public String login(User user) {
        return null;
    }

    @Override
    public User getUserByUsername(String username) {
        return null;
    }
}
