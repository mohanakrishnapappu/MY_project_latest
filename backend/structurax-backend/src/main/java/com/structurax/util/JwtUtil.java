package com.structurax.util;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class JwtUtil {

    private final String SECRET_KEY =
            "StructuraXEnterpriseJWTSecretKey2026SecureAndVeryLongKeyForJWT";

public String generateToken(
        String email,
        String role) {

    return Jwts.builder()
            .subject(email)

            .claim("role", role)

            .issuedAt(new Date())

            .expiration(
                    new Date(
                            System.currentTimeMillis()
                                    + 1000 * 60 * 60 * 24
                    )
            )

            .signWith(
                    SignatureAlgorithm.HS256,
                    SECRET_KEY.getBytes()
            )

            .compact();
}

    public String extractEmail(String token) {

        Claims claims =
                Jwts.parser()
                        .setSigningKey(
                                SECRET_KEY.getBytes()
                        )
                        .build()
                        .parseSignedClaims(token)
                        .getPayload();

        return claims.getSubject();
    }

    public String extractRole(String token) {

        Claims claims =
                Jwts.parser()
                        .setSigningKey(
                                SECRET_KEY.getBytes()
                        )
                        .build()
                        .parseSignedClaims(token)
                        .getPayload();

        return claims.get("role", String.class);
    }

    public boolean validateToken(String token) {

        try {

            Jwts.parser()
                    .setSigningKey(
                            SECRET_KEY.getBytes()
                    )
                    .build()
                    .parseSignedClaims(token);

            return true;

        } catch (Exception e) {

            return false;
        }
    }
}