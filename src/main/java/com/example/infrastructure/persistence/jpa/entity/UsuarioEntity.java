package com.example.infrastructure.persistence.jpa.entity;

import com.example.domain.enums.UserRolEnum;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.LocalDate;
import java.util.Collection;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "users")
public class UsuarioEntity implements UserDetails {

    @Id
    private UUID id;
    private String name;
    private String lastname;

    @Column(unique = true)
    private String documentID;

    @Column(unique = true)
    private String phone;

    @Column(unique = true)
    private String email;
    private String password;
    private LocalDate birthDate;
    private String rol;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(() -> this.rol);
    }

    @Override
    public String getUsername() {
        return this.email;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

}
