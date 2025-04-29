package com.example.application.dto.request;

import com.example.domain.enums.UserRolEnum;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.*;
import org.springframework.security.crypto.bcrypt.BCrypt;

import java.time.LocalDate;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UsuarioOwnerRequestDto {
    @NotNull
    private String name;

    @NotNull
    private String lastname;

    @NotNull
    private Long documentID;

    @Pattern(regexp = "\\+?[0-9]{1,12}")
    private String phone;

    @Email
    private String email;

    @NotNull
    private String password;

    @NotNull
    private LocalDate birthDate;



}
