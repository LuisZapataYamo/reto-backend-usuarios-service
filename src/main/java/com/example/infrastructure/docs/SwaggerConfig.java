package com.example.infrastructure.docs;

import io.swagger.v3.oas.models.media.Content;
import io.swagger.v3.oas.models.media.Schema;
import io.swagger.v3.oas.models.responses.ApiResponse;
import org.springdoc.core.customizers.OpenApiCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenApiCustomizer addAuthResponses() {

        Schema<?> errorSchema = new Schema<>().$ref("#/components/schemas/ErrorResponseDto");


        Content jsonError = new Content().addMediaType(
                org.springframework.http.MediaType.APPLICATION_JSON_VALUE,      // evita choque de clases
                new io.swagger.v3.oas.models.media.MediaType().schema(errorSchema)
        );

        ApiResponse unauthenticated = new ApiResponse()
                .description("No autenticado — token ausente o inválido")
                .content(jsonError);

        ApiResponse forbidden = new ApiResponse()
                .description("No autorizado — el usuario autenticado no posee rol ADMIN")
                .content(jsonError);

        ApiResponse badRequest = new ApiResponse()
                .description("Peticion erronea — los datos de entrada no son válidos")
                .content(jsonError);

        return openApi -> {
            if (openApi.getPaths() == null) return;   // seguridad ante OpenAPI vacío

            openApi.getComponents()
                    .addResponses("Unauthenticated", unauthenticated)
                    .addResponses("Forbidden", forbidden)
                    .addResponses("BadRequest", badRequest);
        };

    }
}
