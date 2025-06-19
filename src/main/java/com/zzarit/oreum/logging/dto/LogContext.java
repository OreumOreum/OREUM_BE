package com.zzarit.oreum.logging.dto;


import com.zzarit.oreum.logging.domain.MemberIdentifier;

public record LogContext(String identifier,
                         MemberIdentifier memberIdentifier,
                         String latency,
                         String httpMethod,
                         String uri,
                         String requestBody,
                         String statusCode,
                         String responseBody,
                         String stackTrace) {
}
