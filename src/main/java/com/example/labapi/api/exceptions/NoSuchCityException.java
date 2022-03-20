package com.example.labapi.api.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class NoSuchCityException extends RuntimeException{
    public NoSuchCityException(String message) {
        super(message);
    }

    public NoSuchCityException(String message, Throwable cause) {
        super(message, cause);
    }

    public NoSuchCityException(Throwable cause) {
        super(cause);
    }

    public NoSuchCityException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

    public NoSuchCityException() {
    }
}
