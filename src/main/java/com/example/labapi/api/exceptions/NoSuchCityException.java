package com.example.labapi.api.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class NoSuchCityException extends RuntimeException{
    public NoSuchCityException(String message) {
        super(message);
    }
}
