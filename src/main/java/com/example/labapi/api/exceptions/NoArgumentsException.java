package com.example.labapi.api.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class NoArgumentsException extends RuntimeException{
    public NoArgumentsException(String message) {
        super(message);
    }

    public NoArgumentsException(String message, Throwable cause) {
        super(message, cause);
    }

    public NoArgumentsException(Throwable cause) {
        super(cause);
    }

    public NoArgumentsException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

    public NoArgumentsException() {
    }
}
