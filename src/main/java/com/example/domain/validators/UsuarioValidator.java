package com.example.domain.validators;

import com.example.domain.constants.UserExceptionConstants;

import static com.example.domain.constants.UserValidatorConstants.*;

import com.example.domain.constants.HttpCodesConstants;
import com.example.domain.enums.UserRolEnum;
import com.example.domain.exception.UsuarioException;
import com.example.domain.model.UsuarioModel;

import java.time.LocalDate;
import java.time.Period;

public class UsuarioValidator {

    private UsuarioValidator() {
        // Prevent instantiation
    }

    public static void validateUserAuthenticated(UsuarioModel userAuthenticate) {
        if (userAuthenticate.equals(new UsuarioModel())) {
            throw new UsuarioException(
                    UserExceptionConstants.USER_NO_AUTHENTICATE.getMessage(),
                    UserExceptionConstants.USER_NO_AUTHENTICATE.name(),
                    HttpCodesConstants.UNAUTHORIZED
            );
        }
    }

    public static void validateCreateUsuarioOwner(UsuarioModel usuario, UserRolEnum userAuthenticatedRol) {

        if (!userAuthenticatedRol.equals(UserRolEnum.ROLE_ADMIN)) {
            throw new UsuarioException(
                    UserExceptionConstants.USER_AUTHENTICATED_NOT_AUTHORIZED.getMessage(),
                    UserExceptionConstants.USER_AUTHENTICATED_NOT_AUTHORIZED.name(),
                    HttpCodesConstants.FORBIDDEN
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

    public static void validateCreateUsuarioEmployee(UsuarioModel usuario, UserRolEnum userAuthenticatedRol) {
        if (!userAuthenticatedRol.equals(UserRolEnum.ROLE_OWNER)) {
            throw new UsuarioException(
                    UserExceptionConstants.USER_AUTHENTICATED_NOT_AUTHORIZED.getMessage(),
                    UserExceptionConstants.USER_AUTHENTICATED_NOT_AUTHORIZED.name(),
                    HttpCodesConstants.FORBIDDEN
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
        validateEmail(usuario.getEmail());
        validatePassword(usuario.getPassword());
    }

    public static void validateUsuarioClient(UsuarioModel usuario) {
        validateName(usuario.getName());
        validateLastname(usuario.getLastname());
        validateDocumentID(usuario.getDocumentID());
        validatePhone(usuario.getPhone());
        validateBirthDate(usuario.getBirthDate());
        validateEmail(usuario.getEmail());
        validatePassword(usuario.getPassword());
    }

    private static void validateName(String name) {
        if (name == null || name.isEmpty()) {
            throw new UsuarioException(
                    UserExceptionConstants.USER_NAME_NULL.getMessage(),
                    UserExceptionConstants.USER_NAME_NULL.name(),
                    HttpCodesConstants.BAD_REQUEST
            );
        }
    }

    private static void validateLastname(String lastname) {
        if (lastname == null || lastname.isEmpty()) {
            throw new UsuarioException(
                    UserExceptionConstants.USER_LASTNAME_NULL.getMessage(),
                    UserExceptionConstants.USER_LASTNAME_NULL.name(),
                    HttpCodesConstants.BAD_REQUEST
            );
        }
    }

    private static void validateDocumentID(String documentID) {
        if (documentID == null || documentID.isEmpty()) {
            throw new UsuarioException(
                    UserExceptionConstants.USER_DOCUMENT_ID_NULL.getMessage(),
                    UserExceptionConstants.USER_DOCUMENT_ID_NULL.name(),
                    HttpCodesConstants.BAD_REQUEST
            );
        }

        if (!DOCUMENT_ID_PATTERN.matcher(documentID).matches()) {
            throw new UsuarioException(
                    UserExceptionConstants.USER_DOCUMENT_ID_FORMAT_INVALID.getMessage(),
                    UserExceptionConstants.USER_DOCUMENT_ID_FORMAT_INVALID.name(),
                    HttpCodesConstants.BAD_REQUEST
            );
        }
    }

    private static void validatePhone(String phone) {
        if (phone == null || phone.isEmpty()) {
            throw new UsuarioException(
                    UserExceptionConstants.USER_PHONE_NULL.getMessage(),
                    UserExceptionConstants.USER_PHONE_NULL.name(),
                    HttpCodesConstants.BAD_REQUEST
            );
        }

        if (!PHONE_PATTERN.matcher(phone).matches()) {
            throw new UsuarioException(
                    UserExceptionConstants.USER_PHONE_FORMAT_INVALID.getMessage(),
                    UserExceptionConstants.USER_PHONE_FORMAT_INVALID.name(),
                    HttpCodesConstants.BAD_REQUEST
            );
        }
    }

    private static void validateBirthDate(LocalDate birthDate) {
        if (birthDate == null) {
            throw new UsuarioException(
                    UserExceptionConstants.USER_BIRTH_DATE_NULL.getMessage(),
                    UserExceptionConstants.USER_BIRTH_DATE_NULL.name(),
                    HttpCodesConstants.BAD_REQUEST
            );
        }
    }

    private static void validateLegalAge(LocalDate birthDate) {
        if (Period.between(birthDate, LocalDate.now()).getYears() < LEGAL_AGE_YEARS) {
            throw new UsuarioException(
                    UserExceptionConstants.USER_NOT_LEGAL_AGE.getMessage(),
                    UserExceptionConstants.USER_NOT_LEGAL_AGE.name(),
                    HttpCodesConstants.BAD_REQUEST
            );
        }
    }

    private static void validateEmail(String email) {
        if (!EMAIL_PATTERN.matcher(email).matches()) {
            throw new UsuarioException(
                    UserExceptionConstants.USER_EMAIL_FORMAT_INVALID.getMessage(),
                    UserExceptionConstants.USER_EMAIL_FORMAT_INVALID.name(),
                    HttpCodesConstants.BAD_REQUEST
            );
        }
    }

    private static void validatePassword(String password) {
        if (password == null || password.isEmpty()) {
            throw new UsuarioException(
                    UserExceptionConstants.USER_PASSWORD_NULL.getMessage(),
                    UserExceptionConstants.USER_PASSWORD_NULL.name(),
                    HttpCodesConstants.BAD_REQUEST
            );
        }
    }
}