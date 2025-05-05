package com.example.domain.service;

import com.example.domain.enums.UserRolEnum;
import com.example.domain.exception.UsuarioException;
import com.example.domain.model.UsuarioModel;
import com.example.domain.port.out.IUsuarioServicePortOut;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CrearUsuarioOwnerTest {

    @Mock
    private IUsuarioServicePortOut usuarioPortOut;

    @InjectMocks
    private CrearUsuario crearUsuario;

    private UsuarioModel validUsuario;

    @BeforeEach
    void setUp() {
        validUsuario = new UsuarioModel();
        validUsuario.setName("Juan");
        validUsuario.setLastname("Pérez");
        validUsuario.setDocumentID("123456789");
        validUsuario.setPhone("+573005698325");
        validUsuario.setBirthDate(LocalDate.of(1990, 1, 1));
        validUsuario.setEmail("juan@example.com");
        validUsuario.setPassword("$2a$10$abcdefghijklmnopqrstu"); // Hash bcrypt dummy
    }

    @Test
    void shouldCreateOwnerUserSuccessfully_whenDataIsValidAndAuthenticatedRoleIsAdmin() {
        when(usuarioPortOut.crearUsuario(any(UsuarioModel.class)))
                .thenAnswer(invocation -> invocation.getArgument(0));

        UsuarioModel created = crearUsuario.crearUsuarioOwner(validUsuario, UserRolEnum.ROLE_ADMIN);

        assertNotNull(created.getId());
        assertEquals(UserRolEnum.ROLE_OWNER, created.getRol());
        verify(usuarioPortOut, times(1)).crearUsuario(any(UsuarioModel.class));
    }

    @Test
    void shouldThrowException_whenAuthenticatedRoleIsNotAdmin() {
        assertThrows(
                UsuarioException.class,
                () -> crearUsuario.crearUsuarioOwner(validUsuario, UserRolEnum.ROLE_OWNER)
        );

        verify(usuarioPortOut, never()).crearUsuario(any());
    }

    @Test
    void shouldThrowException_whenEmailIsInvalid() {
        validUsuario.setEmail("bad-email");

        assertThrows(
                UsuarioException.class,
                () -> crearUsuario.crearUsuarioOwner(validUsuario, UserRolEnum.ROLE_ADMIN)
        );
    }

    @Test
    void shouldThrowException_whenPhoneIsInvalid() {
        validUsuario.setPhone("+57300569832599"); // 14 dígitos

        assertThrows(
                UsuarioException.class,
                () -> crearUsuario.crearUsuarioOwner(validUsuario, UserRolEnum.ROLE_ADMIN)
        );
    }

    @Test
    void shouldThrowException_whenDocumentIdIsNotNumeric() {
        validUsuario.setDocumentID("ABC123");

        assertThrows(
                UsuarioException.class,
                () -> crearUsuario.crearUsuarioOwner(validUsuario, UserRolEnum.ROLE_ADMIN)
        );
    }

    @Test
    void shouldThrowException_whenUserIsUnderAge() {
        validUsuario.setBirthDate(LocalDate.now().minusYears(17));

        assertThrows(
                UsuarioException.class,
                () -> crearUsuario.crearUsuarioOwner(validUsuario, UserRolEnum.ROLE_ADMIN)
        );
    }
}

