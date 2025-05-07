package com.example.infrastructure.security.service;

import com.example.domain.model.UsuarioModel;
import com.example.domain.port.out.IUsuarioServicePortOut;
import com.example.infrastructure.persistence.jpa.entity.UsuarioEntity;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UsuarioServiceDetail implements UserDetailsService {

    private final IUsuarioServicePortOut usuarioServicePortOut;
    private final ObjectMapper objectMapper;

    @Override
    public UsuarioEntity loadUserByUsername(String username) throws UsernameNotFoundException {
        UsuarioModel usuarioModel = usuarioServicePortOut.getUsuarioByEmail(username);
        return objectMapper.convertValue(usuarioModel, UsuarioEntity.class);
    }
}
