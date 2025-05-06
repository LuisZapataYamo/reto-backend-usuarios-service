package com.example.application.usecase.implementions;

import com.example.application.dto.request.UsuarioLoginDto;
import com.example.application.usecase.interfaces.ILoginUseCase;
import com.example.domain.exception.CredentialsException;
import com.example.domain.model.UsuarioModel;
import com.example.domain.port.in.ILoginUsuarioServicePortIn;
import com.example.infrastructure.exception.EntityException;
import com.example.infrastructure.persistence.jpa.entity.UsuarioEntity;
import com.example.infrastructure.security.service.UsuarioServiceDetail;
import com.example.infrastructure.security.utils.JwtUtil;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
@RequiredArgsConstructor
public class LoginUseCase implements ILoginUseCase {
    private final ILoginUsuarioServicePortIn loginService;

    @Override
    public String login(UsuarioLoginDto dto) {
        UsuarioModel usuarioModel = new UsuarioModel();
        usuarioModel.setEmail(dto.getUsername());
        usuarioModel.setPassword(dto.getPassword());
        return loginService.login(usuarioModel);
    }
}
