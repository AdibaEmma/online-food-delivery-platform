package com.aweperi.onlinefooddeliveryassmnt.exceptions;

import lombok.Data;

import java.util.Date;

@Data
public class ErrorMessage {
    private final int statusCode;
    private final Date timestamp;
    private final String message;
    private final String description;
}
