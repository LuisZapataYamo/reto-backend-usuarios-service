package com.example.domain.service;

import com.example.application.dto.request.UsuarioOwnerRequestDto;
import com.example.application.dto.response.UsuarioResponseDto;
import com.example.application.mapper.UsuarioMapper;
import com.example.application.usecase.implementions.CrearUsuarioUseCase;
import com.example.domain.enums.UserRolEnum;
import com.example.domain.model.UsuarioModel;
import com.example.domain.port.in.ICrearUsuarioServicePortIn;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
public class CrearUsuarioTest {

    @Mock
    private ICrearUsuarioServicePortIn crearUsuarioServicePortIn;

    @Mock
    private UsuarioMapper usuarioMapper;

    @InjectMocks
    private CrearUsuarioUseCase crearUsuarioUseCase;

    @Test
    void testCreateUser() {
        // Arrange
        UsuarioOwnerRequestDto requestDto = new UsuarioOwnerRequestDto();
        requestDto.setDocumentID("123456");
        requestDto.setName("John");
        requestDto.setLastname("Doe");
        requestDto.setEmail("john.doe@example.com");
        requestDto.setPassword("password123");
        requestDto.setPhone("+1234567890");

        UsuarioModel requestModel = new UsuarioModel();
        requestModel.setDocumentID("123456");
        requestModel.setName("John");
        requestModel.setLastname("Doe");
        requestModel.setEmail("john.doe@example.com");
        requestModel.setRol(UserRolEnum.OWNER);
        requestModel.setPassword("password123");
        requestModel.setPhone("+1234567890");

        UsuarioModel responseModel = new UsuarioModel();
        responseModel.setId(UUID.randomUUID());
        responseModel.setDocumentID("123456");
        responseModel.setName("John");
        responseModel.setLastname("Doe");
        responseModel.setEmail("john.doe@example.com");
        responseModel.setRol(UserRolEnum.OWNER);
        responseModel.setPassword("password123");
        responseModel.setPhone("+1234567890");

        UsuarioResponseDto responseDto = new UsuarioResponseDto();
        responseDto.setId(responseModel.getId());
        responseDto.setDocumentID("123456");
        responseDto.setName("John");
        responseDto.setLastname("Doe");
        responseDto.setEmail("john.doe@example.com");

        when(usuarioMapper.usuarioOwnerRequestDtoToModel(requestDto)).thenReturn(requestModel);
        when(crearUsuarioServicePortIn.crearUsuarioOwner(requestModel)).thenReturn(responseModel);
        when(usuarioMapper.modelToUsuarioResponse(responseModel)).thenReturn(responseDto);

        // Act
        UsuarioResponseDto result = crearUsuarioUseCase.crearUsuarioOwner(requestDto);

        // Assert
        assertEquals(responseDto, result);
        verify(usuarioMapper).usuarioOwnerRequestDtoToModel(requestDto);
        verify(crearUsuarioServicePortIn).crearUsuarioOwner(requestModel);
        verify(usuarioMapper).modelToUsuarioResponse(responseModel);

    }
}
