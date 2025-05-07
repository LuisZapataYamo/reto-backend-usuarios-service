package com.example.domain.exception;

import com.example.domain.constants.HttpCodesConstants;

public class UsuarioException extends ApiException {
    public UsuarioException(String message, String errorCode, HttpCodesConstants httpStatus) {
        super(message, errorCode, httpStatus);
    }
}
