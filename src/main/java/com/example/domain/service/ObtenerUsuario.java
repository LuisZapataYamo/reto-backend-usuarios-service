package com.example.domain.service;

import com.example.domain.exception.UsuarioException;
import com.example.domain.model.UsuarioModel;
import com.example.domain.port.in.IObtenerUsuarioServicePortIn;
import com.example.domain.port.out.IUsuarioServicePortOut;
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
        UsuarioModel usuarioModel = usuarioServicePortOut.getUsuarioByID(id);

        if(usuarioModel.equals(new UsuarioModel())) {
            throw new UsuarioException(
                    "Usuario no encontrado",
                    "USER_NOT_FOUND",
                    HttpStatus.NOT_FOUND
            );
        }

        return usuarioModel;
    }
}
