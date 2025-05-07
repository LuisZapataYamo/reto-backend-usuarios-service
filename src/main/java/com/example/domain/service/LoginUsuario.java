package com.example.domain.service;

import com.example.domain.constants.HttpCodesConstants;
import com.example.domain.constants.UserExceptionConstants;
import com.example.domain.exception.UsuarioException;
import com.example.domain.model.UsuarioModel;
import com.example.domain.port.in.ILoginUsuarioServicePortIn;
import com.example.domain.port.out.IAuthenticateServicePortOut;
import com.example.domain.port.out.IUsuarioServicePortOut;
import com.example.domain.validators.UsuarioValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class LoginUsuario implements ILoginUsuarioServicePortIn {
    private final IUsuarioServicePortOut usuarioServicePortOut;
    private final IAuthenticateServicePortOut authenticateServicePortOut;

    @Override
    public String login(UsuarioModel usuarioLogin) {

        UsuarioValidator.validateLoginUsuario(usuarioLogin);

        UsuarioModel usuarioModel = usuarioServicePortOut.getUsuarioByEmail(usuarioLogin.getEmail());

        if(usuarioModel.equals(new UsuarioModel())){
            throw new UsuarioException(
                    UserExceptionConstants.USER_NOT_FOUND.getMessage(),
                    UserExceptionConstants.USER_NOT_FOUND.name(),
                    HttpCodesConstants.NOT_FOUND
            );
        }

        boolean isValidPassword = authenticateServicePortOut.isCorrectPassword(usuarioLogin, usuarioModel.getPassword());

        if (!isValidPassword) {
            throw new UsuarioException(
                    UserExceptionConstants.PASSWORD_INCORRECT.getMessage(),
                    UserExceptionConstants.PASSWORD_INCORRECT.name(),
                    HttpCodesConstants.BAD_REQUEST
            );
        }
        return authenticateServicePortOut.getToken(usuarioModel);
    }
}
