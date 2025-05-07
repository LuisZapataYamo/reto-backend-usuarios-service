package com.example.domain.service;

import com.example.domain.constants.HttpCodesConstants;
import com.example.domain.constants.UserExceptionConstants;
import com.example.domain.exception.UsuarioException;
import com.example.domain.model.UsuarioModel;
import com.example.domain.port.in.IObtenerUsuarioServicePortIn;
import com.example.domain.port.out.IUsuarioServicePortOut;
import lombok.RequiredArgsConstructor;
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
                    UserExceptionConstants.USER_NOT_FOUND.getMessage(),
                    UserExceptionConstants.USER_NOT_FOUND.name(),
                    HttpCodesConstants.NOT_FOUND
            );
        }

        return usuarioModel;
    }
}
