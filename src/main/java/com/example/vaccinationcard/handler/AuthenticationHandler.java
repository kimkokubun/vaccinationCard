package com.example.vaccinationcard.handler;

import com.example.vaccinationcard.exceptions.AuthenticationException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class AuthenticationHandler {
    @ResponseStatus(code = HttpStatus.FORBIDDEN)
    @ExceptionHandler(AuthenticationException.class)
    public String handle(AuthenticationException exception) {
        return exception.getMessage();
    }
}
