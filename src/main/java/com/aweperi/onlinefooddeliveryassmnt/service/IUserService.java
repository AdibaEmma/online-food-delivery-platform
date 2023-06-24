package com.aweperi.onlinefooddeliveryassmnt.service;

import com.aweperi.onlinefooddeliveryassmnt.model.User;

public interface IUserService {
    User getUserByUsername(String username);
}
