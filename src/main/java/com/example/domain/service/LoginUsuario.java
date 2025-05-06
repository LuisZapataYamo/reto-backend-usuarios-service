package com.example.domain.service;

import com.example.domain.exception.UsuarioException;
import com.example.domain.model.UsuarioModel;
import com.example.domain.port.in.ILoginUsuarioServicePortIn;
import com.example.domain.port.out.IAuthenticateServicePortOut;
import com.example.domain.port.out.IJwtServicePortOut;
import com.example.domain.port.out.IUsuarioServicePortOut;
import com.example.domain.validators.UsuarioValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
@RequiredArgsConstructor
public class LoginUsuario implements ILoginUsuarioServicePortIn {
    private final IJwtServicePortOut jwtServicePortOut;
    private final IUsuarioServicePortOut usuarioServicePortOut;
    private final IAuthenticateServicePortOut authenticateServicePortOut;

    @Override
    public String login(UsuarioModel usuarioLogin) {

        UsuarioValidator.validateLoginUsuario(usuarioLogin);

        UsuarioModel usuarioModel = usuarioServicePortOut.getUsuarioByEmail(usuarioLogin.getEmail());

        if(usuarioModel.equals(new UsuarioModel())){
            throw new UsuarioException(
                    "User not found",
                    "USER_NOT_FOUND",
                    HttpStatus.NOT_FOUND
            );
        }

        boolean isValidPassword = authenticateServicePortOut.isCorrectPassword(usuarioLogin, usuarioModel.getPassword());

        if (!isValidPassword) {
            throw new UsuarioException(
                    "Password is incorrect",
                    "PASSWORD_INCORRECT",
                    HttpStatus.BAD_REQUEST
            );
        }
        return authenticateServicePortOut.getToken(usuarioModel);
    }
}
