package com.arquitetura.senac.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ControllerException {
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler
    public String livrariaHandlerException(LivrariaException livrariaException) {
        return livrariaException.getMessage();
    }
}
