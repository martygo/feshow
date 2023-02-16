package com.martygo.feshow.config;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.http.HttpHeaders;
import org.springframework.web.context.request.WebRequest;

import org.springframework.validation.FieldError;

@RestControllerAdvice
public class ValidationHandler {

    List<String> errors = new ArrayList<>();

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorCustom> handleValidationExceptions(MethodArgumentNotValidException ex) {
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            
            errors.add(fieldName + ": " + errorMessage);
        });

        ErrorCustom customError = new ErrorCustom(HttpStatus.BAD_REQUEST, ex.getLocalizedMessage(), errors);
        
        return new ResponseEntity<ErrorCustom>(customError, new HttpHeaders(), customError.getStatus());
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorCustom> handleAll(Exception ex, WebRequest request) {
        errors.add(ex.getLocalizedMessage());

        ErrorCustom customError = new ErrorCustom(HttpStatus.INTERNAL_SERVER_ERROR, ex.getLocalizedMessage(), errors);

        return new ResponseEntity<ErrorCustom>(customError, new HttpHeaders(), customError.getStatus());
    }
}
