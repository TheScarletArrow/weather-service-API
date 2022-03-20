package com.example.labapi.api.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class CurrentExceptionHandler {
    @ExceptionHandler(value = NoSuchCityException.class)
    ResponseEntity<?> wrongCity() {
        Map<String,String> response = new HashMap<>();
        response.put("status","City is set wrong in URL");
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
    }

    @ExceptionHandler(value = NoArgumentsException.class)
    @ResponseBody
   ResponseEntity<?>  showCustomMessage(Exception e){
        Map<String,String> response = new HashMap<>();
        response.put("status","City is missing in URL");
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
    }
}
