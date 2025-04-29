package com.example.domain.exception;

import org.springframework.http.HttpStatus;

public class UsuarioNoLegalAge extends UsuarioException {

    public UsuarioNoLegalAge() {
        super(
                "El usuario no tiene la edad legal para registrarse",
                "USER_NOT_LEGAL_AGE",
                HttpStatus.BAD_REQUEST
        );
    }
}
