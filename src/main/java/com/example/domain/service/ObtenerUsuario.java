package com.example.domain.service;

import com.example.domain.model.UsuarioModel;
import com.example.domain.port.in.IObtenerUsuarioServicePortIn;
import com.example.domain.port.out.IUsuarioServicePortOut;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
@RequiredArgsConstructor
public class ObtenerUsuario implements IObtenerUsuarioServicePortIn {

    private final IUsuarioServicePortOut usuarioServicePortOut;

    @Override
    public UsuarioModel obtenerUsuarioPorID(UUID id) {
        return usuarioServicePortOut.getUsuarioByID(id);
    }
}
