package com.example.domain.port.out;

import com.example.domain.model.UsuarioModel;
import com.example.domain.model.request.UsuarioRequestModel;
import com.example.domain.model.response.UsuarioResponseModel;


public interface IUsuarioServicePortOut {
    UsuarioModel crearUsuario(UsuarioModel usuarioModel);
}
