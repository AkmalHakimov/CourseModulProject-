package com.example.lesson10.Controller;

import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.io.FileNotFoundException;

@ControllerAdvice
public class Handler {

    @ExceptionHandler(FileNotFoundException.class)
    public HttpEntity<?> fileNotFound(FileNotFoundException e){
        return ResponseEntity.status(404).body(e.getMessage());
    }

}
