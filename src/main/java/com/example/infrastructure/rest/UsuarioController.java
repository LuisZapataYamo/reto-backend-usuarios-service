package com.example.infrastructure.rest;

import com.example.application.dto.response.UsuarioResponseDto;
import com.example.application.usecase.interfaces.IObtenerUsuarioUseCase;
import com.example.infrastructure.dto.ErrorResponseDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
@Tag(name = "Usuario Controller", description = "Operaciones relacionadas con los usuarios")
public class UsuarioController {

    private final IObtenerUsuarioUseCase obtenerUsuario;

    @Operation(summary = "Obtener un usuario por ID", description = "Devuelve los detalles de un usuario dado su ID",
        responses = {
            @ApiResponse(
                responseCode = "200",
                description = "Usuario encontrado",
                content = @Content(mediaType = "application/json", schema = @Schema(implementation = UsuarioResponseDto.class))
            ), @ApiResponse(
                responseCode = "404",
                description = "Usuario no encontrado",
                content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponseDto.class))
            ),
            @ApiResponse(ref = "#/components/responses/Unauthenticated")
        }
    )
    @GetMapping("/{idUsuario}")
    public ResponseEntity<UsuarioResponseDto> getUsuario(@PathVariable UUID idUsuario) {
        return ResponseEntity.ok(obtenerUsuario.obtenerUsuario(idUsuario));
    }
}
