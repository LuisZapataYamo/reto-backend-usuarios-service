package com.example.application.mapper;

import com.example.application.dto.request.UsuarioEmployeeRequestDto;
import com.example.application.dto.request.UsuarioOwnerRequestDto;
import com.example.application.dto.response.UsuarioResponseDto;
import com.example.domain.model.UsuarioModel;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UsuarioMapper {

    UsuarioResponseDto modelToUsuarioResponse(UsuarioModel usuarioModel);

    UsuarioModel usuarioOwnerRequestDtoToModel(UsuarioOwnerRequestDto usuarioOwnerRequestDto);
    UsuarioModel usuarioEmployeeRequestDtoToModel(UsuarioEmployeeRequestDto usuarioEmployeeRequestDto);
}
