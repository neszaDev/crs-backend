package com.cms.backend.auth.dto;

import java.util.UUID;

public record UserResponse(

        UUID publicId,

        Integer companyId,

        Integer roleId,

        String email,

        String status

) {}