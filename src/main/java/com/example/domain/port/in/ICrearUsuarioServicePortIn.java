package com.example.domain.port.in;

import com.example.domain.enums.UserRolEnum;
import com.example.domain.model.UsuarioModel;

public interface ICrearUsuarioServicePortIn {

    UsuarioModel crearUsuarioOwner(UsuarioModel usuarioRequestModel, UserRolEnum userAuthenticatedRol);
}
