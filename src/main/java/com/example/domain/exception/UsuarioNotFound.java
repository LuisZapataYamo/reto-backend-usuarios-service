package com.example.domain.exception;

import org.springframework.http.HttpStatus;

public class UsuarioNotFound extends UsuarioException {

    public UsuarioNotFound() {
        super(
                "Usuario not found",
                "USER_NOT_FOUND",
                HttpStatus.NOT_FOUND
        );
    }
}
