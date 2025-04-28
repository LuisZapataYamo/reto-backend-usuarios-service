package com.example.domain.validators;

import com.example.domain.model.UsuarioModel;

import java.time.LocalDate;
import java.time.Period;

public class UsuarioValidator {

    public static void validateUsuario(UsuarioModel usuario) {
        validateLegalAge(usuario.getBirthDate());
    }

    private static void validateLegalAge(LocalDate birthDate) {
        if(Period.between(birthDate, LocalDate.now()).getYears() < 18) {
            throw new IllegalArgumentException("El usuario no es mayor de edad");
        }
    }
}
