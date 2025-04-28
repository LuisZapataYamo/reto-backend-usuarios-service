package com.example.domain.exception;

import org.springframework.http.HttpStatus;

public class UsuarioNoLegalAge extends UsuarioException {

    protected UsuarioNoLegalAge(String message, String errorCode, HttpStatus httpStatus) {
        super(
                "El usuario no tiene la edad legal para registrarse",
                "USER_NOT_LEGAL_AGE",
                HttpStatus.BAD_REQUEST
        );
    }
}
