package com.aweperi.onlinefooddeliveryassmnt.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.CONFLICT)
public class RestaurantAlreadyExistsException extends RuntimeException {
    public RestaurantAlreadyExistsException() {
        super("restaurant already exists");
    }

    public RestaurantAlreadyExistsException(String message) {
        super(message);
    }
}
