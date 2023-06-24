package com.aweperi.onlinefooddeliveryassmnt.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class MenuItemNotFoundException extends RuntimeException {
    public MenuItemNotFoundException() {
        super("Menu Item does not exists");
    }

    public MenuItemNotFoundException(String message) {
        super(message);
    }
}
