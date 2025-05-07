package com.example.application.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UsuarioLoginDto {

    @Schema(requiredMode = Schema.RequiredMode.REQUIRED)
    private String username;

    @Schema(requiredMode = Schema.RequiredMode.REQUIRED)
    private String password;
}
