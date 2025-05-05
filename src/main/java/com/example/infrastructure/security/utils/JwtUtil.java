package com.example.infrastructure.security.utils;

import com.example.infrastructure.persistence.jpa.entity.UsuarioEntity;
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
public class JwtUtil {

    @Value("${jwt.sk}")
    private String secretKey;

    private SecretKey hashSecretKey;

    @Value("${jwt.exp}")
    private Long timeExpiration;

    @PostConstruct
    public void init() {
        this.hashSecretKey = Keys.hmacShaKeyFor(secretKey.getBytes());
    }

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

    public Map<String, Object> getClaimsFromToken(String token) {
        return Jwts.parser()
                .verifyWith(hashSecretKey)
                .build()
                .parseSignedClaims(token)
                .getPayload();
    }

    public String getClaimFromToken(String token, String claim) {
        return Jwts.parser()
                .verifyWith(hashSecretKey)
                .build()
                .parseSignedClaims(token)
                .getPayload()
                .get(claim, String.class);
    }

    public String getSubjectFromToken(String token) {
        return Jwts.parser()
                .verifyWith(hashSecretKey)
                .build()
                .parseSignedClaims(token)
                .getPayload()
                .getSubject();
    }

    public Boolean isTokenExpired(String token) {

        Date expiration = Jwts.parser()
                .verifyWith(hashSecretKey)
                .build()
                .parseSignedClaims(token)
                .getPayload()
                .getExpiration();
        return expiration.before(new Date());
    }

    public Boolean validateToken(String token, UsuarioEntity userEntity) {
        boolean isTokenExpired = isTokenExpired(token);
        boolean isUserEnabled = userEntity.isEnabled();
        return !isTokenExpired && isUserEnabled;
    }
}
