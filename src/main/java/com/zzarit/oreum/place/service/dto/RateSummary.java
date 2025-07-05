package com.zzarit.oreum.place.service.dto;

public record RateSummary(Double average, Long count) {
    public Double average() {
        return average != null ? average : 0.0;
    }

    public Long count() {
        return count != null ? count : 0L;
    }
}

