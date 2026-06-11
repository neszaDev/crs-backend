package com.cms.backend.auth.dto;

public record LoginResponse(
        String accessToken,
        String refreshToken
) {}