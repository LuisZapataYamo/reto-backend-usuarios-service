package com.example.application.usecase.interfaces;

import com.example.application.dto.request.UsuarioAdministradorRequestDto;
import com.example.application.dto.response.UsuarioResponseDto;

public interface ICrearUsuarioUseCase {

    UsuarioResponseDto crearUsuarioAdministrador(UsuarioAdministradorRequestDto usuarioRequestModel);

}
