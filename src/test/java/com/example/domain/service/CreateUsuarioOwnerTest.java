package com.example.domain.service;

import com.example.application.dto.request.UsuarioOwnerRequestDto;
import com.example.application.dto.response.UsuarioResponseDto;
import com.example.application.mapper.UsuarioMapper;
import com.example.application.usecase.implementions.CrearUsuarioUseCase;
import com.example.domain.enums.UserRolEnum;
import com.example.domain.exception.UsuarioException;
import com.example.domain.model.UsuarioModel;
import com.example.domain.port.out.IAuthenticateServicePortOut;
import com.example.domain.port.out.IUsuarioServicePortOut;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.mapstruct.factory.Mappers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.function.Consumer;
import java.util.stream.Stream;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class CreateUsuarioOwnerTest {

    public static class UsuarioBuilder {

        private UsuarioModel u = new UsuarioModel();
        private UsuarioBuilder() { }

        public static UsuarioBuilder ownerCandidate() {
            UsuarioBuilder b = new UsuarioBuilder();
            b.u.setName("Juan");
            b.u.setLastname("Pérez");
            b.u.setDocumentID("123456789");
            b.u.setPhone("+573005698325");
            b.u.setBirthDate(LocalDate.of(1990,1,1));
            b.u.setEmail("juan@example.com");
            b.u.setPassword("pwd");
            return b;
        }

        public static UsuarioBuilder admin() { return ownerCandidate().rol(UserRolEnum.ROLE_ADMIN); }
        public static UsuarioBuilder owner() { return ownerCandidate().rol(UserRolEnum.ROLE_OWNER); }

        public UsuarioBuilder rol(UserRolEnum rol) { u.setRol(rol); return this; }
        public UsuarioModel build() { return u; }
    }

    public static class UsuarioDtoMother {
        public static UsuarioOwnerRequestDto validOwnerRequest() {
            UsuarioOwnerRequestDto dto = new UsuarioOwnerRequestDto();
            dto.setName("Juan");
            dto.setLastname("Pérez");
            dto.setDocumentID("123456789");
            dto.setPhone("+573005698325");
            dto.setBirthDate(LocalDate.of(1990,1,1));
            dto.setEmail("juan@example.com");
            dto.setPassword("pwd");
            return dto;
        }
    }


    @Nested
    @ExtendWith(MockitoExtension.class)
    class DomainLayer {

        @Mock
        private IUsuarioServicePortOut usuarioPortOut;

        @Mock
        private IAuthenticateServicePortOut authenticatePortOut;

        @InjectMocks
        private CrearUsuario crearUsuario;

        private UsuarioModel adminAuth;
        private UsuarioModel validUser;

        @BeforeEach
        void init() {
            adminAuth = UsuarioBuilder.admin().build();
            validUser = UsuarioBuilder.ownerCandidate().build();
        }

        @Test
        void shouldThrowException_whenUserNotAuthenticated() {
            when(authenticatePortOut.getUserAuthenticate()).thenReturn(new UsuarioModel());

            assertThrows(UsuarioException.class, () -> crearUsuario.crearUsuarioOwner(validUser));
            verify(usuarioPortOut, never()).crearUsuario(any());
        }

        @Test
        void shouldThrowException_whenAuthenticatedRoleIsNotAdmin() {
            UsuarioModel ownerAuth = new UsuarioModel();
            ownerAuth.setRol(UserRolEnum.ROLE_OWNER);
            when(authenticatePortOut.getUserAuthenticate()).thenReturn(ownerAuth);

            assertThrows(UsuarioException.class, () -> crearUsuario.crearUsuarioOwner(validUser));
            verify(usuarioPortOut, never()).crearUsuario(any());
        }


        @ParameterizedTest(name = "invalid case: {0}")
        @MethodSource("invalidScenarios")
        void createOwner_invalidData(String label, Consumer<UsuarioModel> mutation) {
            when(authenticatePortOut.getUserAuthenticate()).thenReturn(adminAuth);
            mutation.accept(validUser);

            assertThrows(UsuarioException.class, () -> crearUsuario.crearUsuarioOwner(validUser));

            verifyNoInteractions(usuarioPortOut);
        }

        static Stream<Arguments> invalidScenarios() {
            return Stream.of(
                    Arguments.of("email",   (Consumer<UsuarioModel>) u -> u.setEmail("bad")),
                    Arguments.of("phone",   (Consumer<UsuarioModel>) u -> u.setPhone("+57300111223344")),
                    Arguments.of("docID",   (Consumer<UsuarioModel>) u -> u.setDocumentID("ABC")),
                    Arguments.of("age<18",  (Consumer<UsuarioModel>) u -> u.setBirthDate(LocalDate.now().minusYears(17)))
            );
        }
    }

    @Nested
    @ExtendWith(MockitoExtension.class)
    class CrearUsuarioOwnerFlowIT {

        @Mock  IUsuarioServicePortOut usuarioPortOut;
        @Mock  IAuthenticateServicePortOut authPortOut;

        private final UsuarioMapper mapper = Mappers.getMapper(UsuarioMapper.class);
        private CrearUsuarioUseCase useCase;

        @BeforeEach
        void init() {
            useCase = new CrearUsuarioUseCase(
                    new CrearUsuario(usuarioPortOut, authPortOut), mapper);
        }

        @Test
        void fullFlow_ok() {
            UsuarioModel admin = UsuarioBuilder.admin().build();
            when(authPortOut.getUserAuthenticate()).thenReturn(admin);
            when(authPortOut.encryptPassword(any())).thenReturn("$hash");
            when(usuarioPortOut.crearUsuario(any())).thenAnswer(i -> i.getArgument(0));

            UsuarioOwnerRequestDto in = UsuarioDtoMother.validOwnerRequest();
            UsuarioResponseDto out = useCase.crearUsuarioOwner(in);

            assertAll(
                    () -> assertThat(out.getId()).isNotNull(),
                    () -> assertThat(out.getName()).isEqualTo(in.getName()),
                    () -> assertThat(out.getLastname()).isEqualTo(in.getLastname()),
                    () -> assertThat(out.getDocumentID()).isEqualTo(in.getDocumentID()),
                    () -> assertThat(out.getPhone()).isEqualTo(in.getPhone()),
                    () -> assertThat(out.getBirthDate()).isEqualTo(in.getBirthDate()),
                    () -> assertThat(out.getRol()).isEqualTo(UserRolEnum.ROLE_OWNER),
                    () -> assertThat(out.getEmail()).isEqualTo(in.getEmail())
            );
        }
    }
}
