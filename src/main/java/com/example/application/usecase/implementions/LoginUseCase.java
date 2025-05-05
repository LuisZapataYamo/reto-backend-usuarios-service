package com.example.application.usecase.implementions;

import com.example.application.dto.request.UsuarioLoginDto;
import com.example.application.usecase.interfaces.ILoginUseCase;
import com.example.domain.exception.CredentialsException;
import com.example.infrastructure.exception.EntityException;
import com.example.infrastructure.persistence.jpa.entity.UsuarioEntity;
import com.example.infrastructure.security.service.UsuarioServiceDetail;
import com.example.infrastructure.security.utils.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
@RequiredArgsConstructor
public class LoginUseCase implements ILoginUseCase {
    private final UsuarioServiceDetail usuarioServiceDetail;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;

    @Override
    public String login(UsuarioLoginDto dto) {
        try{
            UsuarioEntity user = usuarioServiceDetail.loadUserByUsername(dto.getUsername());

            if (!passwordEncoder.matches(dto.getPassword(), user.getPassword())) {
                throw new CredentialsException(
                        "Credenciales incorrectas", // mensaje de error
                        "PASSWORD_INCORRECT", // código de error
                        HttpStatus.UNAUTHORIZED // código de estado HTTP
                ); // excepción de dominio
            }

            Map<String, Object> claims = Map.of(
                    "role", user.getRol()
            );

            return jwtUtil.generateToken(user.getUsername(), claims);
        }
        catch (EntityException e){
            throw new CredentialsException(
                    "El username no existe", // mensaje de error
                    "USERNAME_NOT_EXISTS", // código de error
                    HttpStatus.UNAUTHORIZED // código de estado HTTP
            ); // excepción de dominio
        }
    }
}
