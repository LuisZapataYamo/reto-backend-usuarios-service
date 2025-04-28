package com.example.infrastructure.security;


import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        return httpSecurity.csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("**").permitAll()
                        //.requestMatchers("/api/v1/owner/**").hasRole("OWNER")
                        //.requestMatchers("/api/v1/client/**").hasRole("CLIENT")
                        //.requestMatchers("/api/v1/employee/**").hasRole("EMPLOYEE")
                        .anyRequest().authenticated()
                )
                .build();
    }

}
