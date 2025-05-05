package com.example.application.usecase.interfaces;

import com.example.application.dto.request.UsuarioLoginDto;

public interface ILoginUseCase {
    String login(UsuarioLoginDto usuarioLoginDto);
}
