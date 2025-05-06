package com.example.domain.model;

import com.example.domain.enums.UserRolEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.Objects;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UsuarioModel {
    private UUID id;
    private String name;
    private String lastname;
    private String documentID;
    private String phone;
    private String email;
    private String password;
    private LocalDate birthDate;
    private UserRolEnum rol;

    public void sanitize(){
        this.name = this.name.trim();
        this.lastname = this.lastname.trim();
        this.documentID = this.documentID.trim();
        this.phone = this.phone.trim();
        this.email = this.email.trim();
        this.password = this.password.trim();
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        UsuarioModel that = (UsuarioModel) o;
        return Objects.equals(id, that.id) && Objects.equals(name, that.name) && Objects.equals(lastname, that.lastname) && Objects.equals(documentID, that.documentID) && Objects.equals(phone, that.phone) && Objects.equals(email, that.email) && Objects.equals(password, that.password) && Objects.equals(birthDate, that.birthDate) && rol == that.rol;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, lastname, documentID, phone, email, password, birthDate, rol);
    }
}
