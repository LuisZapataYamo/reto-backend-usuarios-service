package com.example.infrastructure.rest;

import com.example.application.dto.request.UsuarioEmployeeRequestDto;
import com.example.application.dto.response.UsuarioResponseDto;
import com.example.application.usecase.interfaces.ICrearUsuarioUseCase;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("users/employees")
@RequiredArgsConstructor
public class EmployeeController {
    private final ICrearUsuarioUseCase crearUsuario;

    @Operation(
            summary     = "Crear un usuario empleado",
            description = "Crea un nuevo usuario asignándole automáticamente el rol EMPLOYEE",
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
    @PostMapping("/")
    public ResponseEntity<UsuarioResponseDto> crearUsuario(@Valid @RequestBody UsuarioEmployeeRequestDto usuarioRequestDto) {
        return ResponseEntity.ok(crearUsuario.crearUsuarioEmployee(usuarioRequestDto));
    }
}
