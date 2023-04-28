package com.asj.bootcamp.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletRequest;

@ControllerAdvice
public class ConfigHandlerException {

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<?> handleEnteredDataNotFound(HttpServletRequest request, NotFoundException error){
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                             .body(new ErrorResponse(error.getMessage(),HttpStatus.NOT_FOUND.value()));
    }

}