package com.example.infrastructure.rest;

import com.example.application.dto.request.UsuarioAdministradorRequestDto;
import com.example.application.dto.response.UsuarioResponseDto;
import com.example.application.usecase.interfaces.ICrearUsuarioUseCase;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("users/admin")
@RequiredArgsConstructor
public class UsuarioController {
    private final ICrearUsuarioUseCase crearUsuario;

    @PostMapping("/")
    public ResponseEntity<UsuarioResponseDto> crearUsuario(@Valid @RequestBody UsuarioAdministradorRequestDto usuarioRequestDto) {
         return ResponseEntity.ok(crearUsuario.crearUsuarioAdministrador(usuarioRequestDto));
    }
}
