package com.example.application.usecase.implementions;

import com.example.application.dto.response.UsuarioResponseDto;
import com.example.application.usecase.interfaces.IObtenerUsuarioUseCase;
import com.example.domain.model.UsuarioModel;
import com.example.domain.port.in.IObtenerUsuarioServicePortIn;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ObtenerUsuarioUseCase implements IObtenerUsuarioUseCase {

    private final IObtenerUsuarioServicePortIn obtenerUsuarioServicePortIn;
    private final ObjectMapper objectMapper;

    @Override
    public UsuarioResponseDto obtenerUsuario(UUID id) {
        UsuarioModel usuarioModel = obtenerUsuarioServicePortIn.obtenerUsuarioPorID(id);
        return objectMapper.convertValue(usuarioModel, UsuarioResponseDto.class);
    }
}
