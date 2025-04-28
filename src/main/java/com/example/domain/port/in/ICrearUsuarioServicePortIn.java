package com.example.domain.port.in;

import com.example.domain.model.UsuarioModel;

public interface ICrearUsuarioServicePortIn {

    UsuarioModel crearUsuario(UsuarioModel usuarioRequestModel);
}
