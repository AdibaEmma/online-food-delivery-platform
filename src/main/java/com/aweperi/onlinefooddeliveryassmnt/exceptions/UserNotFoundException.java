package com.aweperi.onlinefooddeliveryassmnt.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class UserNotFoundException extends RuntimeException {
    public UserNotFoundException() {
        super("User account not found on database");
    }

    public UserNotFoundException(String message) {
        super(message);
    }
}
