package com.example.domain.validators;

import com.example.domain.enums.UserRolEnum;
import com.example.domain.exception.UsuarioException;
import com.example.domain.model.UsuarioModel;
import org.springframework.http.HttpStatus;

import java.time.LocalDate;
import java.time.Period;

public class UsuarioValidator {

    private UsuarioValidator() {
        // Prevent instantiation
    }

    public static void validateCreateUsuarioOwner(UsuarioModel usuario, UserRolEnum userAuthenticatedRol) {

        if(!userAuthenticatedRol.equals(UserRolEnum.ROLE_ADMIN)){
            throw new UsuarioException(
                    "El usuario autenticado no tiene permisos para crear un usuario.",
                    "USER_AUTHENTICATED_NOT_ADMIN",
                    HttpStatus.FORBIDDEN
            );
        }

        validateName(usuario.getName());
        validateLastname(usuario.getLastname());
        validateDocumentID(usuario.getDocumentID());
        validatePhone(usuario.getPhone());
        validateBirthDate(usuario.getBirthDate());
        validateEmail(usuario.getEmail());
        validatePassword(usuario.getPassword());
        validateLegalAge(usuario.getBirthDate());
    }

    public static void validateLoginUsuario(UsuarioModel usuario) {
        validateDocumentID(usuario.getDocumentID());
        validatePassword(usuario.getPassword());
    }

    private static void validateName(String name) {
        if(name == null || name.isEmpty()){
            throw new UsuarioException(
                    "El nombre no puede ser nulo o vacío.",
                    "USER_NAME_NULL",
                    HttpStatus.BAD_REQUEST
            );
        }
    }

    private static void validateLastname(String lastname) {
        if(lastname == null || lastname.isEmpty()){
            throw new UsuarioException(
                    "El apellido no puede ser nulo o vacío.",
                    "USER_LASTNAME_NULL",
                    HttpStatus.BAD_REQUEST
            );
        }
    }

    private static void validateDocumentID(String documentID) {

        if(documentID == null || documentID.isEmpty()){
            throw new UsuarioException(
                    "El documento no puede ser nulo o vacío.",
                    "USER_DOCUMENT_ID_NULL",
                    HttpStatus.BAD_REQUEST
            );
        }

        String documentIDRegex = "^\\d+$";
        if (!documentID.matches(documentIDRegex)) {
            throw new UsuarioException(
                    "El documento no tiene un formato válido.",
                    "USER_DOCUMENT_ID_FORMAT_INVALID",
                    HttpStatus.BAD_REQUEST
            );
        }
    }

    private static void validatePhone(String phone) {
        if(phone == null || phone.isEmpty()){
            throw new UsuarioException(
                    "El teléfono no puede ser nulo o vacío.",
                    "USER_PHONE_NULL",
                    HttpStatus.BAD_REQUEST
            );
        }

        String phoneRegex = "^\\+?\\d{0,12}$";
        if (!phone.matches(phoneRegex)) {
            throw new UsuarioException(
                    "El teléfono no tiene un formato válido.",
                    "USER_PHONE_FORMAT_INVALID",
                    HttpStatus.BAD_REQUEST
            );
        }
    }

    private static void validateBirthDate(LocalDate birthDate) {
        if (birthDate == null) {
            throw new UsuarioException(
                    "La fecha de nacimiento no puede ser nula.",
                    "USER_BIRTH_DATE_NULL",
                    HttpStatus.BAD_REQUEST
            );
        }
    }

    private static void validateLegalAge(LocalDate birthDate) {
        if(Period.between(birthDate, LocalDate.now()).getYears() < 18) {
            throw new UsuarioException(
                    "El usuario no tiene la edad legal para registrarse",
                    "USER_NOT_LEGAL_AGE",
                    HttpStatus.BAD_REQUEST
            );
        }
    }

    private static void validateEmail(String email) {
        String emailRegex = "^[\\w.%+-]+@[\\w.-]+\\.[a-zA-Z]{2,}$";
        if (!email.matches(emailRegex)) {
            throw new UsuarioException(
                    "El correo electrónico no tiene un formato válido.",
                    "USER_EMAIL_FORMAT_INVALID",
                    HttpStatus.BAD_REQUEST
            );
        }
    }

    private static void validatePassword(String password) {
        if (password == null || password.isEmpty()) {
            throw new UsuarioException(
                    "La contraseña no puede ser nula o vacía.",
                    "USER_PASSWORD_NULL",
                    HttpStatus.BAD_REQUEST
            );
        }
    }
}
