package com.aweperi.onlinefooddeliveryassmnt.controller;

import com.aweperi.onlinefooddeliveryassmnt.dto.UserDTO;
import com.aweperi.onlinefooddeliveryassmnt.model.User;
import com.aweperi.onlinefooddeliveryassmnt.service.IUserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.text.ParseException;

@Component
@RequiredArgsConstructor
@Slf4j
public class UserServiceFacade {
    private final IUserService userService;

    @Autowired
    private ModelMapper modelMapper;

    public Object registerUser(UserDTO requestBody) {
        try {
            var user = convertToEntity(requestBody);
            userService.registerUser(user);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
        return null;
    }

    public String loginUser(UserDTO userDTO) {
        String token;
        try {
            var user = convertToEntity(userDTO);
            token = userService.login(user);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
        return token;
    }

    public UserDTO getUserByUsername(String username) {
        return convertToDto(userService.getUserByUsername(username));
    }

    private UserDTO convertToDto(User user) {
        return modelMapper.map(user, UserDTO.class);
    }

    private User convertToEntity(UserDTO userDTO) throws ParseException {
        return modelMapper.map(userDTO, User.class);
    }
}