package com.example.domain.service;

import com.example.application.dto.request.UsuarioAdministradorRequestDto;
import com.example.application.dto.response.UsuarioResponseDto;
import com.example.application.mapper.UsuarioMapper;
import com.example.application.usecase.implementions.CrearUsuarioUseCase;
import com.example.domain.enums.UserRolEnum;
import com.example.domain.model.UsuarioModel;
import com.example.domain.port.in.ICrearUsuarioServicePortIn;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
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

    //@BeforeEach
    //void setUp() {
    //    MockitoAnnotations.openMocks(this);
    //}

    @Test
    void testCreateUser() {
        // Arrange
        UsuarioAdministradorRequestDto requestDto = new UsuarioAdministradorRequestDto();
        requestDto.setDocumentID(123456L);
        requestDto.setName("John");
        requestDto.setLastname("Doe");
        requestDto.setEmail("john.doe@example.com");
        requestDto.setPassword("password123");
        requestDto.setPhone("+1234567890");

        UsuarioModel requestModel = new UsuarioModel();
        requestModel.setDocumentID(123456L);
        requestModel.setName("John");
        requestModel.setLastname("Doe");
        requestModel.setEmail("john.doe@example.com");
        requestModel.setRol(UserRolEnum.OWNER);
        requestModel.setPassword("password123");
        requestModel.setPhone("+1234567890");

        UsuarioModel responseModel = new UsuarioModel();
        responseModel.setId(UUID.randomUUID());
        responseModel.setDocumentID(123456L);
        responseModel.setName("John");
        responseModel.setLastname("Doe");
        responseModel.setEmail("john.doe@example.com");
        responseModel.setRol(UserRolEnum.OWNER);
        responseModel.setPassword("password123");
        responseModel.setPhone("+1234567890");

        UsuarioResponseDto responseDto = new UsuarioResponseDto();
        responseDto.setId(responseModel.getId());
        responseDto.setDocumentID(123456L);
        responseDto.setName("John");
        responseDto.setLastname("Doe");
        responseDto.setEmail("john.doe@example.com");

        when(usuarioMapper.usuarioAdministradorRequestDtoToUsuarioRequestModel(requestDto)).thenReturn(requestModel);
        when(crearUsuarioServicePortIn.crearUsuario(requestModel)).thenReturn(responseModel);
        when(usuarioMapper.usuarioAdminRequestDtoToModel(responseModel)).thenReturn(responseDto);

        // Act
        UsuarioResponseDto result = crearUsuarioUseCase.crearUsuarioAdministrador(requestDto);

        // Assert
        assertEquals(responseDto, result);
        verify(usuarioMapper).usuarioAdministradorRequestDtoToUsuarioRequestModel(requestDto);
        verify(crearUsuarioServicePortIn).crearUsuario(requestModel);
        verify(usuarioMapper).usuarioAdminRequestDtoToModel(responseModel);

    }
}
