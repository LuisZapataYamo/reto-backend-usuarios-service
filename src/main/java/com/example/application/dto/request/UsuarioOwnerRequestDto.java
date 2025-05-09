package com.example.application.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UsuarioOwnerRequestDto {
    @Schema(requiredMode = Schema.RequiredMode.REQUIRED)
    private String name;

    @Schema(requiredMode = Schema.RequiredMode.REQUIRED)
    private String lastname;

    @Schema(requiredMode = Schema.RequiredMode.REQUIRED)
    private String documentID;

    @Schema(requiredMode = Schema.RequiredMode.REQUIRED)
    private String phone;

    @Schema(requiredMode = Schema.RequiredMode.REQUIRED)
    private String email;

    @Schema(requiredMode = Schema.RequiredMode.REQUIRED)
    private String password;

    @Schema(requiredMode = Schema.RequiredMode.REQUIRED)
    private LocalDate birthDate;



}
