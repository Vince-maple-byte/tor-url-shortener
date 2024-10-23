package com.vince.tor_url_shortener.controller;

import com.vince.tor_url_shortener.exception.UrlNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler()
    public ResponseEntity<Object> handleUrlNotFoundException(UrlNotFoundException urlNotFoundException){
        return new ResponseEntity<>(urlNotFoundException.getMessage(), HttpStatus.NOT_FOUND);
    }
}
