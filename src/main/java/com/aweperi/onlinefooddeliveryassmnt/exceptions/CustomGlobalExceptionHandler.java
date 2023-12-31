package com.aweperi.onlinefooddeliveryassmnt.exceptions;

import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestControllerAdvice
@Slf4j
public class CustomGlobalExceptionHandler extends ResponseEntityExceptionHandler {
    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
                                                                  @NonNull HttpHeaders headers,
                                                                  HttpStatusCode status,
                                                                  @NonNull WebRequest request) {
        Map<String, Object> body = new LinkedHashMap<>();
        body.put("timestamp", new Date());
        body.put("status", status.value());

        List<String> errors = ex.getBindingResult()
                .getFieldErrors()
                .stream()
                .map(fieldError -> String.format("%s %s", fieldError.getField(),
                        fieldError.getDefaultMessage()))
                .collect(Collectors.toList());
        body.put("errors", errors);
        return new ResponseEntity<>(body, headers, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = RestaurantNotFoundException.class)
    @ResponseStatus(value = HttpStatus.NOT_FOUND)
    public ErrorMessage restaurantNotFoundException(RestaurantNotFoundException ex) {
        log.error(ex.getMessage());
        return new ErrorMessage(HttpStatus.NOT_FOUND.value(), new Date(),
                "Restaurant Not Found Error", ex.getMessage());
    }

    @ExceptionHandler(value = UserNotFoundException.class)
    @ResponseStatus(value = HttpStatus.NOT_FOUND)
    public ErrorMessage userNotFoundException(UserNotFoundException ex) {
        log.error(ex.getMessage());
        return new ErrorMessage(HttpStatus.NOT_FOUND.value(), new Date(),
                "User Not Found Error", ex.getMessage());
    }

    @ExceptionHandler(value = OrderNotFoundException.class)
    @ResponseStatus(value = HttpStatus.NOT_FOUND)
    public ErrorMessage orderNotFoundException(OrderNotFoundException ex) {
        log.error(ex.getMessage());
        return new ErrorMessage(HttpStatus.NOT_FOUND.value(), new Date(),
                "Order Not Found Error", ex.getMessage());
    }

    @ExceptionHandler(value = RestaurantAlreadyExistsException.class)
    @ResponseStatus(value = HttpStatus.CONFLICT)
    public ErrorMessage restaurantAlreadyExistsException(RestaurantAlreadyExistsException ex) {
        log.error(ex.getMessage());
        return new ErrorMessage(HttpStatus.CONFLICT.value(), new Date(),
                "Existing Restaurant Entity Error", ex.getMessage());
    }

    @ExceptionHandler(value = UserAlreadyExistsException.class)
    @ResponseStatus(value = HttpStatus.CONFLICT)
    public ErrorMessage userAlreadyExistsException(UserAlreadyExistsException ex) {
        log.error(ex.getMessage());
        return new ErrorMessage(HttpStatus.CONFLICT.value(), new Date(),
                "Existing User Entity Error", ex.getMessage());
    }

    @ExceptionHandler(value = ClassNotFoundException.class)
    @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
    public ErrorMessage classNotFoundException(ClassNotFoundException ex) {
        return new ErrorMessage(HttpStatus.INTERNAL_SERVER_ERROR.value(),
                new Date(),
                "An unknown error occurred",
                ex.getMessage());
    }

}
