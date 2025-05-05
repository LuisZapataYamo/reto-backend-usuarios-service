package com.example.domain.exception;

import org.springframework.http.HttpStatus;

public class UsuarioException extends ApiException {
    public UsuarioException(String message, String errorCode, HttpStatus httpStatus) {
        super(message, errorCode, httpStatus);
    }
}
