package com.zzarit.oreum.auth.service.dto;


import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public record KakaoLoginResponseDto(
        @JsonProperty("id") Long id
) {
    @JsonCreator(mode = JsonCreator.Mode.PROPERTIES)
    public KakaoLoginResponseDto {}
}
