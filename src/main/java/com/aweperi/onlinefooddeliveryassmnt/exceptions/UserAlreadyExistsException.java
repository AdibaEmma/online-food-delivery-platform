package com.aweperi.onlinefooddeliveryassmnt.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.CONFLICT)
public class UserAlreadyExistsException extends RuntimeException {
    public UserAlreadyExistsException() {
        super("User already exists in our database");
    }

    public UserAlreadyExistsException(String message) {
        super(message);
    }
}
