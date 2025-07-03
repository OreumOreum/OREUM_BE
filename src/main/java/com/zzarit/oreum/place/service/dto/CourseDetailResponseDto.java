package com.zzarit.oreum.place.service.dto;
import com.zzarit.oreum.place.domain.Course;
import java.util.List;

public record CourseDetailResponseDto(
        String title,
        Integer sigunguCode,
        CourseDetailDto detailInfo,
        List<PlaceResponseDto> places,
        String overview
) {

    public static CourseDetailResponseDto from(Course course) {
        List<PlaceResponseDto> placeResponseDtoList = course.getPlaces().stream()
                .map(place -> new PlaceResponseDto(
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
                course.getSigunguCode(),
                course.getCourseDetail().toDto(),
                placeResponseDtoList,
                course.getOverview()
        );
    }

    public record PlaceResponseDto(
            String title,
            String address,
            Double mapX,
            Double mapY,
            String originImage,
            String thumbnailImage
    ) {}

    //    public static class ReviewResponseDto{
//        private Double rating;
//        private Type type;
//        private String content;
//        private LocalDateTime updatedAt;
//    }
}



