package com.example.labapi.api.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;
/**
 * @author Anton Yurkov
 * @version 0.0.5
 */
@ResponseStatus(HttpStatus.NOT_FOUND)
public class WrongDTException extends RuntimeException {
    public WrongDTException() {
    }

    public WrongDTException(String message) {
        super(message);
    }

    public WrongDTException(String message, Throwable cause) {
        super(message, cause);
    }

    public WrongDTException(Throwable cause) {
        super(cause);
    }

    public WrongDTException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
