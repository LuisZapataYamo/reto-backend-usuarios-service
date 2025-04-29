package com.example.infrastructure.rest;

import com.example.application.dto.request.UsuarioAdministradorRequestDto;
import com.example.application.dto.response.UsuarioResponseDto;
import com.example.application.usecase.interfaces.ICrearUsuarioUseCase;
import com.example.application.usecase.interfaces.IObtenerUsuarioUseCase;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UsuarioController {
    private final ICrearUsuarioUseCase crearUsuario;
    private final IObtenerUsuarioUseCase obtenerUsuario;

    @PostMapping("/admin")
    public ResponseEntity<UsuarioResponseDto> crearUsuario(@Valid @RequestBody UsuarioAdministradorRequestDto usuarioRequestDto) {
         return ResponseEntity.ok(crearUsuario.crearUsuarioAdministrador(usuarioRequestDto));
    }

    @GetMapping("/{idUsuario}")
    public ResponseEntity<UsuarioResponseDto> getUsuario(@PathVariable UUID idUsuario) {
        return ResponseEntity.ok(obtenerUsuario.obtenerUsuario(idUsuario));
    }
}
