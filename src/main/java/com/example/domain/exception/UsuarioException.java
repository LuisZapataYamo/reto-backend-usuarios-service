package com.example.domain.exception;

import org.springframework.http.HttpStatus;

public abstract class UsuarioException extends ApiException {
    protected UsuarioException(String message, String errorCode, HttpStatus httpStatus) {
        super(message, errorCode, httpStatus);
    }
}
