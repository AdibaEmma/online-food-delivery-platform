package com.aweperi.onlinefooddeliveryassmnt.service;

import com.aweperi.onlinefooddeliveryassmnt.dto.UserDTO;
import com.aweperi.onlinefooddeliveryassmnt.model.User;

public interface IUserService {
    void registerUser(User user);
    String login(User user);
    User getUserByUsername(String username);
}
