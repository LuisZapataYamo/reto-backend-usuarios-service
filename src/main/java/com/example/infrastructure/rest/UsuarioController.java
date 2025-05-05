package com.example.infrastructure.rest;

import com.example.application.dto.response.UsuarioResponseDto;
import com.example.application.usecase.interfaces.IObtenerUsuarioUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UsuarioController {

    private final IObtenerUsuarioUseCase obtenerUsuario;

    @GetMapping("/{idUsuario}")
    public ResponseEntity<UsuarioResponseDto> getUsuario(@PathVariable UUID idUsuario) {
        return ResponseEntity.ok(obtenerUsuario.obtenerUsuario(idUsuario));
    }
}
