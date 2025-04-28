package com.example.infrastructure.persistence.jpa.repository;

import com.example.infrastructure.persistence.jpa.entity.UsuarioEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public interface JpaUsuarioRepository extends JpaRepository<UsuarioEntity, UUID> {
}
