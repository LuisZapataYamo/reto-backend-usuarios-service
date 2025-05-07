package com.example.domain.port.in;

import com.example.domain.model.UsuarioModel;

public interface ICrearUsuarioServicePortIn {

    UsuarioModel crearUsuarioOwner(UsuarioModel usuarioRequestModel);
    UsuarioModel crearUsuarioEmployee(UsuarioModel usuarioRequestModel);
}
