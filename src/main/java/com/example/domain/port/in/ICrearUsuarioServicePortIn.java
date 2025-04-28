package com.example.domain.port.in;

import com.example.domain.model.UsuarioModel;
import com.example.domain.model.request.UsuarioRequestModel;
import com.example.domain.model.response.UsuarioResponseModel;

public interface ICrearUsuarioServicePortIn {

    UsuarioModel crearUsuario(UsuarioModel usuarioRequestModel);
}
