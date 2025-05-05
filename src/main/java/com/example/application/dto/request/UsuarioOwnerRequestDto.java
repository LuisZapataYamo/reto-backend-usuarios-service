package com.example.application.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.*;

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
    private String documentID;

    @Pattern(regexp = "\\+?\\d{1,12}")
    private String phone;

    @Email
    private String email;

    @NotNull
    private String password;

    @NotNull
    private LocalDate birthDate;



}
