package com.aweperi.onlinefooddeliveryassmnt.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND)
public class RestaurantNotFoundException extends RuntimeException{
    public RestaurantNotFoundException() {
        super("restaurant not found");
    }

    public RestaurantNotFoundException(String message) {
        super(message);
    }
}
