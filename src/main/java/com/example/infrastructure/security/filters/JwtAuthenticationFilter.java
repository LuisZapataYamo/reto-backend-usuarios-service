package com.example.infrastructure.security.filters;


import com.example.infrastructure.constants.JwtExceptionsConstants;
import com.example.infrastructure.dto.ErrorResponseDto;
import com.example.infrastructure.constants.JwtClaimsConstants;
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
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.List;

@Component
@RequiredArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private final JwtUtil jwtUtil;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {

        String authorizationHeader = request.getHeader("Authorization");
        try {
            if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
                String token = authorizationHeader.replace("Bearer ", "");
                if (SecurityContextHolder.getContext().getAuthentication() == null) {

                    String role = jwtUtil.getClaimFromToken(token, JwtClaimsConstants.ROLE_FIELD);

                    if(role == null) {
                        manejarError(
                                response,
                                JwtExceptionsConstants.TOKEN_INVALID_CODE,
                                JwtExceptionsConstants.TOKEN_NO_CONTAINT_ROLE_MESSAGE
                        );
                    }

                    UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(
                            null, token, List.of(new SimpleGrantedAuthority(role)));
                    SecurityContextHolder.getContext().setAuthentication(authentication);

                }
            }
            filterChain.doFilter(request, response);

        } catch (ExpiredJwtException e) {
            manejarError(response, JwtExceptionsConstants.TOKEN_EXPIRED_CODE, JwtExceptionsConstants.TOKEN_EXPIRED_MESSAGE);
        } catch (JwtException e) {
            manejarError(response, JwtExceptionsConstants.TOKEN_INVALID_CODE, JwtExceptionsConstants.TOKEN_INVALID_MESSAGE);
        }
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
