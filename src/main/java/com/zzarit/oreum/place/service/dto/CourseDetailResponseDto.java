package com.zzarit.oreum.place.service.dto;
import com.zzarit.oreum.place.domain.Course;
import java.util.List;

public record CourseDetailResponseDto(
        String title,
        String middleCategory,
        Integer sigunguCode,
        String overview,
        String originImage,
        Double averageRate,
        Long reviewCount,
        List<PlaceResponseDto> places

) {

    public static CourseDetailResponseDto from(Course course,RateSummary rateSummary) {
        List<PlaceResponseDto> placeResponseDtoList = course.getPlaces().stream()
                .map(place -> new PlaceResponseDto(
                        place.getId(),
                        place.getTitle(),
                        place.getAddress(),
                        place.getMapx(),
                        place.getMapy(),
                        place.getOriginImage(),
                        place.getThumbnailImage()
                ))
                .toList();



        return new CourseDetailResponseDto(
                course.getTitle(),
                course.getCategory2(),
                course.getPlaces().get(0).getSigunguCode(),
                course.getOverview(),
                course.getOriginImage(),
                rateSummary.average(),
                rateSummary.count(),
                placeResponseDtoList
        );
    }

    public record PlaceResponseDto(
            Long id,
            String title,
            String address,
            Double mapX,
            Double mapY,
            String originImage,
            String thumbnailImage
    ) {}

}



