package com.ProjectForBNYM.ExceptionHandling;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import java.util.Date;

@ControllerAdvice
public class ControllerExceptionHandler {
    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ErrorMessageModel> resourceNotFoundException(ResourceNotFoundException ex, WebRequest request) {
       ErrorMessageModel message = new ErrorMessageModel(
               HttpStatus.NOT_FOUND.value(),
               new Date(),
               ex.getMessage(),
               request.getDescription(false));
       return new ResponseEntity<>(message, HttpStatus.NOT_FOUND);
    }
}
