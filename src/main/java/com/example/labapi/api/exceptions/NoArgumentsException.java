package com.example.labapi.api.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class NoArgumentsException extends RuntimeException{
    public NoArgumentsException(String message) {
        super(message);
    }
}
