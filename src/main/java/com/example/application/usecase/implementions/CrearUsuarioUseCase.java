package com.example.application.usecase.implementions;

import com.example.application.dto.request.UsuarioAdministradorRequestDto;
import com.example.application.dto.response.UsuarioResponseDto;
import com.example.application.mapper.UsuarioMapper;
import com.example.application.usecase.interfaces.ICrearUsuarioUseCase;
import com.example.domain.model.UsuarioModel;
import com.example.domain.port.in.ICrearUsuarioServicePortIn;
import com.example.domain.model.response.UsuarioResponseModel;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CrearUsuarioUseCase implements ICrearUsuarioUseCase {

    private final ICrearUsuarioServicePortIn crearUsuarioServicePortIn;
    private final UsuarioMapper usuarioMapper;
    //private final ObjectMapper objectMapper;

    @Override
    public UsuarioResponseDto crearUsuarioAdministrador(UsuarioAdministradorRequestDto usuarioAdministradorRequestDto) {
        UsuarioModel response = crearUsuarioServicePortIn.crearUsuario(usuarioMapper.usuarioAdministradorRequestDtoToUsuarioRequestModel(usuarioAdministradorRequestDto));
        return usuarioMapper.usuarioAdminRequestDtoToModel(response);
    }
}
