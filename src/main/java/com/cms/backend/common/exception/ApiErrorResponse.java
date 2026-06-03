package com.cms.backend.common.exception;

import java.time.Instant;
import java.util.List;

public record ApiErrorResponse(
        String type,
        String title,
        int status,
        String detail,
        String errorCode,
        String path,
        List<FieldErrorResponse> fieldErrors,
        Instant timestamp
) {
}