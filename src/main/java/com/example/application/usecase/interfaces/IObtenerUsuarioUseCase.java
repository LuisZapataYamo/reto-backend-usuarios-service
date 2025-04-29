package com.example.application.usecase.interfaces;

import com.example.application.dto.response.UsuarioResponseDto;

import java.util.UUID;

public interface IObtenerUsuarioUseCase {
    UsuarioResponseDto obtenerUsuario(UUID id);
}
