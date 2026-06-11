package com.cms.backend.auth.jwt;

import java.nio.charset.StandardCharsets;
import java.time.Instant;
import java.util.Date;

import javax.crypto.SecretKey;

import org.springframework.stereotype.Service;

import com.cms.backend.auth.entity.User;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;

@Service
public class JwtService {

        private final JwtProperties jwtProperties;

        public JwtService(
                        JwtProperties jwtProperties) {

                this.jwtProperties = jwtProperties;
        }

        private SecretKey getSigningKey() {

                return Keys.hmacShaKeyFor(
                                jwtProperties.secret()
                                                .getBytes(StandardCharsets.UTF_8));
        }

        public String generateAccessToken(
                        User user) {

                Instant now = Instant.now();

                return Jwts.builder()
                                .subject(
                                                user.getPublicId().toString())
                                .claim(
                                                "userId",
                                                user.getPublicId().toString())
                                .claim(
                                                "email",
                                                user.getEmail())
                                .claim(
                                                "role",
                                                user.getRole().getName())
                                .issuedAt(
                                                Date.from(now))
                                .expiration(
                                                Date.from(
                                                                now.plusMillis(
                                                                                jwtProperties.accessTokenExpiration())))
                                .signWith(
                                                getSigningKey(),
                                                Jwts.SIG.HS256)
                                .compact();
        }

        public String generateRefreshToken(
                        User user) {

                Instant now = Instant.now();

                return Jwts.builder()
                                .subject(
                                                user.getPublicId().toString())
                                .issuedAt(
                                                Date.from(now))
                                .expiration(
                                                Date.from(
                                                                now.plusMillis(
                                                                                jwtProperties.refreshTokenExpiration())))
                                .signWith(
                                                getSigningKey(),
                                                Jwts.SIG.HS256)
                                .compact();
        }

        private Claims extractAllClaims(
                        String token) {

                return Jwts.parser()
                                .verifyWith(getSigningKey())
                                .build()
                                .parseSignedClaims(token)
                                .getPayload();
        }

        public String extractUserId(
                        String token) {

                return extractAllClaims(token)
                                .get("userId", String.class);
        }

        public String extractEmail(
                        String token) {

                return extractAllClaims(token)
                                .get("email", String.class);
        }

        public String extractRole(
                        String token) {

                return extractAllClaims(token)
                                .get("role", String.class);
        }

        public String extractSubject(
                        String token) {

                return extractAllClaims(token)
                                .getSubject();
        }

        public boolean isTokenExpired(
                        String token) {

                return extractAllClaims(token)
                                .getExpiration()
                                .before(new Date());
        }

        public boolean isTokenValid(
                        String token,
                        User user) {

                String subject = extractSubject(token);

                return subject.equals(
                                user.getPublicId().toString())
                                && !isTokenExpired(token);
        }
}