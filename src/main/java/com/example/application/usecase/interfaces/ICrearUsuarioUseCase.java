package com.example.application.usecase.interfaces;

import com.example.application.dto.request.UsuarioEmployeeRequestDto;
import com.example.application.dto.request.UsuarioOwnerRequestDto;
import com.example.application.dto.response.UsuarioResponseDto;

public interface ICrearUsuarioUseCase {

    UsuarioResponseDto crearUsuarioOwner(UsuarioOwnerRequestDto usuarioRequestModel);
    UsuarioResponseDto crearUsuarioEmployee(UsuarioEmployeeRequestDto usuarioRequestModel);
    UsuarioResponseDto crearUsuarioClient(UsuarioEmployeeRequestDto usuarioRequestModel);

}
