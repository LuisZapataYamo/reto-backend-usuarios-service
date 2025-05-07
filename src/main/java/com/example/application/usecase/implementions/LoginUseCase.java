package com.example.application.usecase.implementions;

import com.example.application.dto.request.UsuarioLoginDto;
import com.example.application.usecase.interfaces.ILoginUseCase;
import com.example.domain.model.UsuarioModel;
import com.example.domain.port.in.ILoginUsuarioServicePortIn;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

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
