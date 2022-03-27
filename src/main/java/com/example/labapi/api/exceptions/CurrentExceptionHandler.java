package com.example.labapi.api.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;
/**
 * @author Anton Yurkov
 * @version 0.0.5
 */
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
   ResponseEntity<?>  showCustomMessage(){
        Map<String,String> response = new HashMap<>();
        response.put("status","City is missing in URL");
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
    }

    @ExceptionHandler(value = WrongDTException.class)
    ResponseEntity<?> showWrongDT(){
        Map<String,String> response = new HashMap<>();
        response.put("status","dt is set wrong in URL");
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
    }
}
