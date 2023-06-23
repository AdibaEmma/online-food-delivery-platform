package com.aweperi.onlinefooddeliveryassmnt.service;

import com.aweperi.onlinefooddeliveryassmnt.dto.UserDTO;
import com.aweperi.onlinefooddeliveryassmnt.model.User;

public interface IUserService {
    void registerUser(UserDTO userDTO);
    String login(UserDTO userDTO);
    User getUserByUsername(String username);
}
