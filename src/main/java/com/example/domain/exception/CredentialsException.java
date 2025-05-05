package com.example.domain.exception;

import org.springframework.http.HttpStatus;

public class CredentialsException extends ApiException {

    public CredentialsException(String message, String errorCode, HttpStatus httpStatus) {
        super(message, errorCode, httpStatus);
    }
}
