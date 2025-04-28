package com.example.infrastructure.persistence.jpa.entity;

import com.example.domain.enums.UserRolEnum;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "users")
public class UsuarioEntity {

    @Id
    private UUID id;
    private String name;
    private String lastname;

    @Column(unique = true)
    private Long documentID;

    @Column(unique = true)
    private String phone;

    @Column(unique = true)
    private String email;
    private String password;
    private LocalDate birthDate;
    private UserRolEnum rol;

    private LocalDate createAt;
    private LocalDate updateAt;

    @PrePersist
    private void onCreate() {
        this.id = UUID.randomUUID();
        this.createAt = LocalDate.now();
        this.updateAt = LocalDate.now();
    }

    @PreUpdate
    private void onUpdate() {
        this.updateAt = LocalDate.now();
    }
}
