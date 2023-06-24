package com.aweperi.onlinefooddeliveryassmnt.service;

import com.aweperi.onlinefooddeliveryassmnt.model.User;
import com.aweperi.onlinefooddeliveryassmnt.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService implements IUserService, UserDetailsService {
    private final UserRepository userRepository;

    @Override
    public void registerUser(User user) {

    }

    @Override
    public String login(User user) {
        return null;
    }

    @Override
    public User getUserByUsername(String username) {
        return userRepository.findByEmail(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return getUserByUsername(username);
    }
}
