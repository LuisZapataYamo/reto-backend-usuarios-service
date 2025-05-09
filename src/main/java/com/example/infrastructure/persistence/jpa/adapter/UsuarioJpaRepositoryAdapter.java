package com.example.infrastructure.persistence.jpa.adapter;

import com.example.domain.model.UsuarioModel;
import com.example.domain.port.out.IUsuarioServicePortOut;
import com.example.infrastructure.persistence.jpa.entity.UsuarioEntity;
import com.example.infrastructure.persistence.jpa.repository.JpaUsuarioRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.UUID;


@Repository
@RequiredArgsConstructor
public class UsuarioJpaRepositoryAdapter implements IUsuarioServicePortOut {
    private final JpaUsuarioRepository jpaUsuarioRepository;
    private final ObjectMapper objectMapper;

    @Override
    public UsuarioModel crearUsuario(UsuarioModel usuarioRequestModel) {
        UsuarioEntity usuarioEntity = objectMapper.convertValue(usuarioRequestModel, UsuarioEntity.class);
        UsuarioEntity saved = jpaUsuarioRepository.save(usuarioEntity);
        return objectMapper.convertValue(saved, UsuarioModel.class);
    }

    @Override
    public UsuarioModel getUsuarioByID(UUID usuarioID) {
        UsuarioEntity usuarioEntity = jpaUsuarioRepository.findById(usuarioID).orElse(new UsuarioEntity());
        return objectMapper.convertValue(usuarioEntity, UsuarioModel.class);
    }

    @Override
    public UsuarioModel getUsuarioByDocumenId(String documentId) {
        UsuarioEntity usuarioEntity = jpaUsuarioRepository.findByDocumentID(documentId).orElse(new UsuarioEntity());
        return objectMapper.convertValue(usuarioEntity, UsuarioModel.class);
    }

    @Override
    public UsuarioModel getUsuarioByEmail(String email) {
        UsuarioEntity usuarioEntity = jpaUsuarioRepository.findByEmail(email).orElse(new UsuarioEntity());
        return objectMapper.convertValue(usuarioEntity, UsuarioModel.class);
    }


}
