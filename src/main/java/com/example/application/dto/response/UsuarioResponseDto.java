package com.example.application.dto.response;

import com.example.domain.enums.UserRolEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.UUID;


@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class UsuarioResponseDto {
    private UUID id;
    private String name;
    private String lastname;
    private String documentID;
    private String phone;
    private String email;
    private LocalDate birthDate;
    private UserRolEnum rol;
}
