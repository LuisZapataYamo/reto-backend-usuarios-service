package com.example.domain.service;

import com.example.domain.enums.UserRolEnum;
import com.example.domain.model.UserAuthenticatedModel;
import com.example.domain.model.UsuarioModel;
import com.example.domain.port.in.ICrearUsuarioServicePortIn;
import com.example.domain.port.out.IAuthenticateServicePortOut;
import com.example.domain.port.out.IUsuarioServicePortOut;
import com.example.domain.validators.UsuarioValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
@RequiredArgsConstructor
public class CrearUsuario implements ICrearUsuarioServicePortIn {
    private final IUsuarioServicePortOut crearUsuarioServicePortOut;
    private final IAuthenticateServicePortOut authenticateServicePortOut;

    @Override
    public UsuarioModel crearUsuarioOwner(UsuarioModel usuarioRequestModel) {
        UsuarioModel userAuthenticate = authenticateServicePortOut.getUserAuthenticate();
        usuarioRequestModel.setRol(UserRolEnum.ROLE_OWNER);
        usuarioRequestModel.sanitize();

        UsuarioValidator.validateCreateUsuarioOwner(usuarioRequestModel, userAuthenticate.getRol());



        usuarioRequestModel.setId(UUID.randomUUID());

        String passwordEncrypted = authenticateServicePortOut.encryptPassword(usuarioRequestModel.getPassword());
        usuarioRequestModel.setPassword(passwordEncrypted);

        return crearUsuarioServicePortOut.crearUsuario(usuarioRequestModel);
    }
}
