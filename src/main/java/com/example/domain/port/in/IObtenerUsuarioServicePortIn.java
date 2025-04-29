package com.example.domain.port.in;

import com.example.domain.model.UsuarioModel;

import java.util.UUID;

public interface IObtenerUsuarioServicePortIn {

    UsuarioModel obtenerUsuarioPorID(UUID id);
}
