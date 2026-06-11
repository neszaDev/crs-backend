package com.cms.backend.auth.service;

import org.springframework.stereotype.Service;

import com.cms.backend.auth.dto.LoginRequest;
import com.cms.backend.auth.dto.LoginResponse;
import com.cms.backend.auth.entity.User;
import com.cms.backend.auth.jwt.JwtService;
import com.cms.backend.auth.repository.UserRepository;

@Service
public class AuthService {

    private final UserRepository userRepository;
    private final PasswordService passwordService;
    private final JwtService jwtService;

    public AuthService(
            UserRepository userRepository,
            PasswordService passwordService,
            JwtService jwtService) {

        this.userRepository = userRepository;
        this.passwordService = passwordService;
        this.jwtService = jwtService;
    }

    public LoginResponse login(
            LoginRequest request) {

        User user =
                userRepository
                        .findByEmailAndDeletedAtIsNull(
                                request.email())
                        .orElseThrow(() ->
                                new RuntimeException(
                                        "Invalid email or password"));

        if (!"active".equalsIgnoreCase(
                user.getStatus())) {

            throw new RuntimeException(
                    "Account is disabled");
        }

        boolean passwordMatches =
                passwordService.matches(
                        request.password(),
                        user.getPasswordHash());

        if (!passwordMatches) {

            throw new RuntimeException(
                    "Invalid email or password");
        }

        String accessToken =
                jwtService.generateAccessToken(
                        user);

        String refreshToken =
                jwtService.generateRefreshToken(
                        user);

        return new LoginResponse(
                accessToken,
                refreshToken);
    }
}