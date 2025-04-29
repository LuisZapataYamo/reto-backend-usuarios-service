package com.example.domain.validators;

import com.example.domain.exception.UsuarioNoLegalAge;
import com.example.domain.model.UsuarioModel;

import java.time.LocalDate;
import java.time.Period;

public class UsuarioValidator {

    public static void validateUsuarioOwner(UsuarioModel usuario) {
        validateName(usuario.getName());
        validateLastname(usuario.getLastname());
        validateDocumentID(usuario.getDocumentID());
        validatePhone(usuario.getPhone());
        validateBirthDate(usuario.getBirthDate());
        validateEmail(usuario.getEmail());
        validatePassword(usuario.getPassword());
        validateLegalAge(usuario.getBirthDate());
    }

    private static void validateName(String name) {
        if(name == null || name.isEmpty()){
            throw new IllegalArgumentException("La URL del logo no puede ser nula o vacía.");
        }
    }

    private static void validateLastname(String lastname) {
        if(lastname == null || lastname.isEmpty()){
            throw new IllegalArgumentException("La URL del logo no puede ser nula o vacía.");
        }
    }

    private static void validateDocumentID(String documentID) {

        if(documentID == null || documentID.isEmpty()){
            throw new IllegalArgumentException("El documento no puede ser nulo o vacío.");
        }

        String documentIDRegex = "^[0-9]+$";
        if (!documentID.matches(documentIDRegex)) {
            throw new IllegalArgumentException("El documento no tiene un formato válido.");
        }
    }

    private static void validatePhone(String phone) {
        if(phone == null || phone.isEmpty()){
            throw new IllegalArgumentException("El teléfono no puede ser nulo o vacío.");
        }

        String phoneRegex = "^\\+?[0-9]{0,12}$";
        if (!phone.matches(phoneRegex)) {
            throw new RuntimeException("El teléfono no tiene un formato válido.");
        }
    }

    private static void validateBirthDate(LocalDate birthDate) {
        if (birthDate == null) {
            throw new IllegalArgumentException("La fecha de nacimiento no puede ser nula.");
        }
    }

    private static void validateLegalAge(LocalDate birthDate) {
        if(Period.between(birthDate, LocalDate.now()).getYears() < 18) {
            throw new UsuarioNoLegalAge();
        }
    }

    private static void validateEmail(String email) {
        String emailRegex = "^[\\w._%+-]+@[\\w.-]+\\.[a-zA-Z]{2,}$";
        if (!email.matches(emailRegex)) {
            throw new RuntimeException("El email no tiene un formato válido.");
        }
    }

    private static void validatePassword(String password) {
        if (password == null || password.isEmpty()) {
            throw new IllegalArgumentException("La contraseña no puede ser nula o vacía.");
        }
    }
}
