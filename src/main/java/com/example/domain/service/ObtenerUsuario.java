package com.example.domain.service;

import com.example.domain.exception.UsuarioException;
import com.example.domain.model.UsuarioModel;
import com.example.domain.port.in.IObtenerUsuarioServicePortIn;
import com.example.domain.port.out.IUsuarioServicePortOut;
import com.example.infrastructure.exception.EntityException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
@RequiredArgsConstructor
public class ObtenerUsuario implements IObtenerUsuarioServicePortIn {

    private final IUsuarioServicePortOut usuarioServicePortOut;

    @Override
    public UsuarioModel obtenerUsuarioPorID(UUID id) {
        try{
            return usuarioServicePortOut.getUsuarioByID(id);
        }catch(EntityException e){
            throw new UsuarioException(
                    "Error al obtener el usuario con ID: " + id,
                    "USER_NOT_FOUND",
                    HttpStatus.NOT_FOUND
            );
        }
    }
}
