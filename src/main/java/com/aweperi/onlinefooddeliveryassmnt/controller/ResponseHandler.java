package com.aweperi.onlinefooddeliveryassmnt.controller;

import io.micrometer.common.lang.Nullable;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.LinkedHashMap;

@Slf4j
public class ResponseHandler {
    private static final LinkedHashMap<String, Object> jsonResponse = new LinkedHashMap<>();
    public static ResponseEntity<?> handleResponseBody(HttpStatus statusCode, String message, @Nullable Object payload) {
        jsonResponse.put("status", statusCode.value());
        jsonResponse.put("message", message);
        jsonResponse.put("payload", payload);
        if (payload == null) jsonResponse.put("payload", null);

        log.info(String.valueOf(jsonResponse));
        return ResponseEntity.status(statusCode).body(jsonResponse);
    }
}
