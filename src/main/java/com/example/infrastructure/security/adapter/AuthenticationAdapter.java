package com.example.infrastructure.security.adapter;

import com.example.domain.enums.UserRolEnum;
import com.example.domain.model.UsuarioModel;
import com.example.domain.port.out.IAuthenticateServicePortOut;
import com.example.infrastructure.constants.JwtClaimsConstants;
import com.example.infrastructure.security.utils.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
@RequiredArgsConstructor
public class AuthenticationAdapter implements IAuthenticateServicePortOut {
    private final JwtUtil jwtUtil;
    private final PasswordEncoder passwordEncoder;

    @Override
    public UsuarioModel getUserAuthenticate() {
        try {
            String token = SecurityContextHolder.getContext().getAuthentication().getCredentials().toString();
            String email = jwtUtil.getSubjectFromToken(token);
            String rol = jwtUtil.getClaimFromToken(token, JwtClaimsConstants.ROLE_FIELD);
            UsuarioModel userAuthenticate = new UsuarioModel();
            userAuthenticate.setEmail(email);
            userAuthenticate.setRol(UserRolEnum.valueOf(rol));
            return userAuthenticate;
        }catch (Exception e){
            return new UsuarioModel();
        }
    }

    @Override
    public boolean isCorrectPassword(UsuarioModel usuarioModel, String passwordEncoded) {
        return passwordEncoder.matches(usuarioModel.getPassword(), passwordEncoded);
    }

    @Override
    public String getToken(UsuarioModel usuarioModel) {
        String subject = usuarioModel.getEmail();
        Map<String, Object> claims = new HashMap<>();
        claims.put(JwtClaimsConstants.ROLE_FIELD, usuarioModel.getRol().toString());
        claims.put(JwtClaimsConstants.ID_FIELD, usuarioModel.getId());
        return jwtUtil.generateToken(subject, claims);
    }

    @Override
    public String encryptPassword(String password) {
        return passwordEncoder.encode(password);
    }
}
