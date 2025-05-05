package com.example.infrastructure.rest;

import com.example.application.dto.request.UsuarioOwnerRequestDto;
import com.example.application.dto.response.UsuarioResponseDto;
import com.example.application.usecase.interfaces.ICrearUsuarioUseCase;
import com.example.infrastructure.persistence.jpa.entity.UsuarioEntity;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users/owners")
@RequiredArgsConstructor
public class OwnerController {
    private final ICrearUsuarioUseCase crearUsuario;

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/")
    public ResponseEntity<UsuarioResponseDto> crearUsuario(@Valid @RequestBody UsuarioOwnerRequestDto usuarioRequestDto, @AuthenticationPrincipal UsuarioEntity userAuhtneticated) {
        return ResponseEntity.ok(crearUsuario.crearUsuarioOwner(usuarioRequestDto, userAuhtneticated.getRol()));
    }

}
