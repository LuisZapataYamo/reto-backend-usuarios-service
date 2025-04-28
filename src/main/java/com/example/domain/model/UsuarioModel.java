package com.example.domain.model;

import com.example.domain.enums.UserRolEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.crypto.bcrypt.BCrypt;

import java.time.LocalDate;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UsuarioModel {
    private UUID id;
    private String name;
    private String lastname;
    private Long documentID;
    private String phone;
    private String email;
    private String password;
    private LocalDate birthDate;
    private UserRolEnum rol;

    private void encryptPassword() {
        this.password = BCrypt.hashpw(this.password, BCrypt.gensalt());
    }

    private void setRole() {
        this.rol = UserRolEnum.OWNER;
    }

    public void sanitize(){
        this.encryptPassword();
        this.setRole();
    }
}
