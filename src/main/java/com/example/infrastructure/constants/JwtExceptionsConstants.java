package com.example.infrastructure.constants;

public final class JwtExceptionsConstants {

    private JwtExceptionsConstants() {}

    public static final String TOKEN_INVALID_CODE = "TOKEN_INVALID";
    public static final String TOKEN_INVALID_MESSAGE = "El token es inválido";

    public static final String TOKEN_EXPIRED_CODE = "TOKEN_EXPIRED";
    public static final String TOKEN_EXPIRED_MESSAGE = "El token ha expirado";

    public static final String TOKEN_NO_CONTAINT_ROLE_MESSAGE = "El token no contiene el rol del usuario";

}
