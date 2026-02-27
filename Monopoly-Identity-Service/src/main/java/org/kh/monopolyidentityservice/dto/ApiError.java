package org.kh.monopolyidentityservice.dto;

import org.kh.monopolyidentityservice.exception.TraceId;

import java.time.Instant;

public record ApiError(String code, String message, String traceId, Instant timestamp) {
    public static ApiError of(String code, String message) {
        return new ApiError(code, message, TraceId.current(),Instant.now());
    }
}
