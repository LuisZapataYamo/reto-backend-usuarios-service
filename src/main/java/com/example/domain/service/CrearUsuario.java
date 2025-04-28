package com.example.domain.service;

import com.example.domain.model.UsuarioModel;
import com.example.domain.port.in.ICrearUsuarioServicePortIn;
import com.example.domain.port.out.IUsuarioServicePortOut;
import com.example.domain.model.request.UsuarioRequestModel;
import com.example.domain.model.response.UsuarioResponseModel;
import com.example.domain.validators.UsuarioValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CrearUsuario implements ICrearUsuarioServicePortIn {
    private final IUsuarioServicePortOut crearUsuarioServicePortOut;

    @Override
    public UsuarioModel crearUsuario(UsuarioModel usuarioRequestModel) {
        usuarioRequestModel.sanitize();
        UsuarioValidator.validateUsuario(usuarioRequestModel);
        return crearUsuarioServicePortOut.crearUsuario(usuarioRequestModel);
    }
}
