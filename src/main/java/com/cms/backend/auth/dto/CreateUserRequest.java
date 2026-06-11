package com.cms.backend.auth.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record CreateUserRequest(

        @NotNull
        Integer companyId,

        @NotNull
        Integer roleId,

        @Email
        @NotBlank
        String email,

        @NotBlank
        String password,

        String status

) {}