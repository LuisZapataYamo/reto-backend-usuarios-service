package com.example.application.mapper;

import com.example.application.dto.request.UsuarioAdministradorRequestDto;
import com.example.application.dto.response.UsuarioResponseDto;
import com.example.domain.model.UsuarioModel;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UsuarioMapper {

    UsuarioResponseDto usuarioAdminRequestDtoToModel(UsuarioModel usuariorResponseModel);

    UsuarioModel usuarioAdministradorRequestDtoToUsuarioRequestModel(UsuarioAdministradorRequestDto usuarioAdministradorRequestDto);
}
