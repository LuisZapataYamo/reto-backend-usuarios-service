package com.example.domain.service;

import com.example.domain.enums.UserRolEnum;
import com.example.domain.model.UsuarioModel;
import com.example.domain.port.in.ICrearUsuarioServicePortIn;
import com.example.domain.port.out.IUsuarioServicePortOut;
import com.example.domain.validators.UsuarioValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
@RequiredArgsConstructor
public class CrearUsuario implements ICrearUsuarioServicePortIn {
    private final IUsuarioServicePortOut crearUsuarioServicePortOut;

    @Override
    public UsuarioModel crearUsuarioOwner(UsuarioModel usuarioRequestModel, UserRolEnum rol) {
        usuarioRequestModel.setRol(UserRolEnum.ROLE_OWNER);
        usuarioRequestModel.sanitize();
        UsuarioValidator.validateCreateUsuarioOwner(usuarioRequestModel, rol);
        usuarioRequestModel.setId(UUID.randomUUID());
        return crearUsuarioServicePortOut.crearUsuario(usuarioRequestModel);
    }
}
