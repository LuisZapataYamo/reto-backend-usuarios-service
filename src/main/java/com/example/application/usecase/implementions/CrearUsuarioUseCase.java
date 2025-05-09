package com.example.application.usecase.implementions;

import com.example.application.dto.request.UsuarioEmployeeRequestDto;
import com.example.application.dto.request.UsuarioOwnerRequestDto;
import com.example.application.dto.response.UsuarioResponseDto;
import com.example.application.mapper.UsuarioMapper;
import com.example.application.usecase.interfaces.ICrearUsuarioUseCase;
import com.example.domain.model.UsuarioModel;
import com.example.domain.port.in.ICrearUsuarioServicePortIn;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CrearUsuarioUseCase implements ICrearUsuarioUseCase {

    private final ICrearUsuarioServicePortIn crearUsuarioServicePortIn;
    private final UsuarioMapper usuarioMapper;


    @Override
    public UsuarioResponseDto crearUsuarioOwner(UsuarioOwnerRequestDto usuarioOwnerRequestDto) {
        UsuarioModel response = crearUsuarioServicePortIn.crearUsuarioOwner(usuarioMapper.usuarioOwnerRequestDtoToModel(usuarioOwnerRequestDto));
        return usuarioMapper.modelToUsuarioResponse(response);
    }

    @Override
    public UsuarioResponseDto crearUsuarioEmployee(UsuarioEmployeeRequestDto usuarioEmployeeRequestDto) {
        UsuarioModel response = crearUsuarioServicePortIn.crearUsuarioEmployee(usuarioMapper.usuarioEmployeeRequestDtoToModel(usuarioEmployeeRequestDto));
        return usuarioMapper.modelToUsuarioResponse(response);
    }

    @Override
    public UsuarioResponseDto crearUsuarioClient(UsuarioEmployeeRequestDto usuarioClientRequestModel) {
        UsuarioModel response = crearUsuarioServicePortIn.crearUsuarioClient(usuarioMapper.usuarioEmployeeRequestDtoToModel(usuarioClientRequestModel));
        return usuarioMapper.modelToUsuarioResponse(response);
    }
}
