package com.zzarit.oreum.place.domain;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Getter
public enum ContentType {
    NATURE("12"),
    CULTURE("14"),
    COURSE("15"),
    LEPORTS("25"),
    LODGE("28"),
    SHOPPING("38"),
    FOOD("39");

    private final String code;

    public static List<String> codes() {
        return Arrays.stream(values())
                .map(ContentType::getCode)
                .collect(Collectors.toList());
    }
}
