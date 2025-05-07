package com.example.infrastructure.rest;

import com.example.application.dto.request.UsuarioOwnerRequestDto;
import com.example.application.dto.response.UsuarioResponseDto;
import com.example.application.usecase.interfaces.ICrearUsuarioUseCase;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users/owners")
@RequiredArgsConstructor
@Tag(name = "Owner Controller", description = "Operaciones relacionadas con los propietarios")
public class OwnerController {
    private final ICrearUsuarioUseCase crearUsuario;

    @Operation(
            summary     = "Crear un usuario propietario",
            description = "Crea un nuevo usuario asignándole automáticamente el rol OWNER",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description  = "Usuario creado satisfactoriamente",
                            content      = @Content(
                                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    schema    = @Schema(implementation = UsuarioResponseDto.class)
                            )
                    ),
                    @ApiResponse(ref = "#/components/responses/BadRequest"),
                    @ApiResponse(ref = "#/components/responses/Forbidden"),
                    @ApiResponse(ref = "#/components/responses/Unauthenticated")
            }
    )
    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/")
    public ResponseEntity<UsuarioResponseDto> crearUsuario(@Valid @RequestBody UsuarioOwnerRequestDto usuarioRequestDto) {
        return ResponseEntity.ok(crearUsuario.crearUsuarioOwner(usuarioRequestDto));
    }
}
