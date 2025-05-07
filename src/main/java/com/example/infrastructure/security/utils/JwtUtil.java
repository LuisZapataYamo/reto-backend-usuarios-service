package com.example.infrastructure.security.utils;

import com.example.domain.port.out.IJwtServicePortOut;
import io.jsonwebtoken.Jwts;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import io.jsonwebtoken.security.Keys;

import javax.crypto.SecretKey;
import java.util.Date;
import java.util.Map;

@Component
@RequiredArgsConstructor
public class JwtUtil implements IJwtServicePortOut {

    @Value("${jwt.sk}")
    private String secretKey;

    private SecretKey hashSecretKey;

    @Value("${jwt.exp}")
    private Long timeExpiration;

    @PostConstruct
    public void init() {
        this.hashSecretKey = Keys.hmacShaKeyFor(secretKey.getBytes());
    }

    @Override
    public String generateToken(String subject , Map<String, Object> claims) {
        Date now = new Date();
        Date dateExpiration = new Date(now.getTime() + 1000 * timeExpiration); // 1 hora
        return Jwts.builder()
                .subject(subject)
                .claims(claims)
                .issuedAt(now)
                .expiration(dateExpiration)
                .signWith(hashSecretKey)
                .compact();
    }

    @Override
    public String getClaimFromToken(String token, String claim) {
        return Jwts.parser()
                .verifyWith(hashSecretKey)
                .build()
                .parseSignedClaims(token)
                .getPayload()
                .get(claim, String.class);
    }

    @Override
    public String getSubjectFromToken(String token) {
        return Jwts.parser()
                .verifyWith(hashSecretKey)
                .build()
                .parseSignedClaims(token)
                .getPayload()
                .getSubject();
    }

}
