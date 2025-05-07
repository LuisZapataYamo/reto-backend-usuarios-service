package com.example.domain.constants;

import lombok.Getter;

@Getter
public enum UserExceptionConstants {

    USER_NO_AUTHENTICATE("User no esta autenticado"),
    PASSWORD_INCORRECT("Password es incorrecta"),
    USER_NOT_FOUND("User no encontrado"),
    USER_AUTHENTICATED_NOT_AUTHORIZED("El usuario autenticado no esta autorizado para esta accion."),

    // UsuarioValidator
    USER_NAME_NULL("El nombre no puede ser nulo o vacío."),
    USER_LASTNAME_NULL("El apellido no puede ser nulo o vacío."),
    USER_DOCUMENT_ID_NULL("El documento no puede ser nulo o vacío."),
    USER_DOCUMENT_ID_FORMAT_INVALID("El documento no tiene un formato válido."),
    USER_PHONE_NULL("El teléfono no puede ser nulo o vacío."),
    USER_PHONE_FORMAT_INVALID("El teléfono no tiene un formato válido."),
    USER_BIRTH_DATE_NULL("La fecha de nacimiento no puede ser nula."),
    USER_NOT_LEGAL_AGE("El usuario no tiene la edad legal para registrarse."),
    USER_EMAIL_FORMAT_INVALID("El correo electrónico no tiene un formato válido."),
    USER_PASSWORD_NULL("La contraseña no puede ser nula o vacía.");

    private final String message;

    UserExceptionConstants(String message) {
        this.message = message;
    }

}