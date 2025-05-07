package com.example.domain.port.out;

import com.example.domain.model.UsuarioModel;


public interface IAuthenticateServicePortOut {
    UsuarioModel getUserAuthenticate();
    boolean isCorrectPassword(UsuarioModel usuarioModel, String password);
    String getToken(UsuarioModel usuarioModel);
    String encryptPassword(String password);
}
