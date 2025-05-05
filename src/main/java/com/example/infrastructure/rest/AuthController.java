package com.example.infrastructure.rest;

import com.example.application.dto.request.UsuarioLoginDto;
import com.example.application.usecase.interfaces.ILoginUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users/auth")
@RequiredArgsConstructor
public class AuthController {
    private final ILoginUseCase loginUseCase;

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody UsuarioLoginDto usuarioLoginDto) {
        return ResponseEntity.ok(loginUseCase.login(usuarioLoginDto));
    }

}
