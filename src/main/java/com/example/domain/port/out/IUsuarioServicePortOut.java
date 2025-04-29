package com.example.domain.port.out;

import com.example.domain.model.UsuarioModel;

import java.util.UUID;


public interface IUsuarioServicePortOut {
    UsuarioModel crearUsuario(UsuarioModel usuarioModel);

    UsuarioModel getUsuarioByID(UUID usuarioID);
}
