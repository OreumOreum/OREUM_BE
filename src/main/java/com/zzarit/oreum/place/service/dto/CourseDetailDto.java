package com.zzarit.oreum.place.service.dto;

public record CourseDetailDto(
    String distance,                // 코스 총 거리
    String infocentertourcourse,    // 문의 및 안내
    String schedule,                // 코스 일정
    String taketime,                // 코스 총 소요 시간
    String theme                    // 코스 테마
) {}