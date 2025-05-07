package com.example.domain.port.out;

import java.util.Map;

public interface IJwtServicePortOut {
    String generateToken(String subject, Map<String, Object> claims);

    String getSubjectFromToken(String token);

    String getClaimFromToken(String token, String claim);
}
