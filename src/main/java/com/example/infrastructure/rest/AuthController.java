package com.example.infrastructure.rest;

import com.example.application.dto.request.UsuarioEmployeeRequestDto;
import com.example.application.dto.request.UsuarioLoginDto;
import com.example.application.dto.response.UsuarioResponseDto;
import com.example.application.usecase.interfaces.ICrearUsuarioUseCase;
import com.example.application.usecase.interfaces.ILoginUseCase;
import com.example.infrastructure.dto.ErrorResponseDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users/auth")
@RequiredArgsConstructor
@Tag(name = "Auth Controller", description = "Operaciones relacionadas con la autenticación")
public class AuthController {
    private final ILoginUseCase loginUseCase;
    private final ICrearUsuarioUseCase crearUsuarioUseCase;

    @Operation(summary = "Iniciar sesión", description = "Autentica a un usuario y devuelve un token JWT",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Inicio de sesión exitoso",
                            content = @Content(mediaType = "application/text", schema = @Schema(type = "string"))
                    ),
                    @ApiResponse(
                            responseCode = "404",
                            description = "El usuario no fue encontrado",
                            content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponseDto.class))
                    ),
                    @ApiResponse(ref = "#/components/responses/BadRequest")
            }
    )
    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody UsuarioLoginDto usuarioLoginDto) {
        return ResponseEntity.ok(loginUseCase.login(usuarioLoginDto));
    }


    @Operation(summary = "Registrar cliente", description = "Registra un nuevo cliente en el sistema",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Registro exitoso",
                            content = @Content(mediaType = "application/text", schema = @Schema(type = "string"))
                    ),
                    @ApiResponse(
                            responseCode = "409",
                            description = "Conflicto en la solicitud",
                            content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponseDto.class))
                    ),
                    @ApiResponse(ref = "#/components/responses/BadRequest")
            }
    )
    @PostMapping("/register")
    public ResponseEntity<UsuarioResponseDto> register(@RequestBody UsuarioEmployeeRequestDto usuarioRegisterDto) {
        return ResponseEntity.ok(crearUsuarioUseCase.crearUsuarioClient(usuarioRegisterDto));
    }
}
