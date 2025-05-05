package com.example.infrastructure.security.filters;


import com.example.infrastructure.dto.ErrorResponseDto;
import com.example.infrastructure.persistence.jpa.entity.UsuarioEntity;
import com.example.infrastructure.security.service.UsuarioServiceDetail;
import com.example.infrastructure.security.utils.JwtUtil;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.JwtException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.List;

@Component
@RequiredArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private final JwtUtil jwtUtil;
    private final UsuarioServiceDetail userDetailsService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {

        String authorizationHeader = request.getHeader("Authorization");
        try {
            if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
                String token = authorizationHeader.replace("Bearer ", "");
                if (SecurityContextHolder.getContext().getAuthentication() == null) {

                    if (jwtUtil.getClaimFromToken(token, "client_id") != null) {
                        autenticarMicroservicio(token);
                    } else {
                        autenticarUsuarioHumano(token);
                    }
                }
            }
            filterChain.doFilter(request, response);

        } catch (ExpiredJwtException e) {
            manejarError(response, "Token expired", e.getMessage());
        } catch (JwtException e) {
            manejarError(response, "Token not valid", e.getMessage());
        }
    }

    private void autenticarUsuarioHumano(String token) {
        String username = jwtUtil.getSubjectFromToken(token);
        UsuarioEntity userEntity = userDetailsService.loadUserByUsername(username);
        if (Boolean.TRUE.equals(jwtUtil.validateToken(token, userEntity))) {
            UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(
                    userEntity, null, userEntity.getAuthorities());
            SecurityContextHolder.getContext().setAuthentication(authentication);
        }
    }


    private void autenticarMicroservicio(String token) {
        String clientId = jwtUtil.getClaimFromToken(token, "client_id");
        String role = jwtUtil.getClaimFromToken(token, "role");

        UsuarioEntity servicio = new UsuarioEntity();
        servicio.setDocumentID(clientId);
        servicio.setRol(role != null ? role : "ROLE_SERVICE");

        UsernamePasswordAuthenticationToken auth = new UsernamePasswordAuthenticationToken(
                servicio, null, servicio.getAuthorities());
        SecurityContextHolder.getContext().setAuthentication(auth);
    }

    private void manejarError(HttpServletResponse response, String error, String mensaje) throws IOException {
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        response.setContentType("application/json");

        ErrorResponseDto errorDto = new ErrorResponseDto(
                List.of(error),
                mensaje
        );

        response.getWriter().write(new ObjectMapper().writeValueAsString(errorDto));
    }
}
