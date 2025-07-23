package com.zzarit.oreum.scheduler.service;

import com.zzarit.oreum.place.domain.Course;
import com.zzarit.oreum.place.domain.Place;
import com.zzarit.oreum.place.domain.repository.CourseRepository;
import com.zzarit.oreum.place.domain.repository.PlaceRepository;
import com.zzarit.oreum.scheduler.client.OpenApiClient;
import com.zzarit.oreum.scheduler.client.dto.AreaBasedDto;
import com.zzarit.oreum.scheduler.client.dto.OpenApiResponseDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
@Slf4j
@RequiredArgsConstructor
public class SynchronizeService {

    private final OpenApiClient openApiClient;
    private final PlaceRepository placeRepository;
    private final CourseRepository courseRepository;
    private static final String COURSETYPE = "C01";

    @Transactional
    public void savePlaceAndCourse() {
        int pageNo = 1;
        while (true) {
            OpenApiResponseDto<AreaBasedDto> dto = openApiClient.getAreaBasedList(pageNo++, 1000);
            if (dto.getResponse().getBody().getItems() == null) break;

            List<AreaBasedDto> items = dto.getResponse().getBody().getItems().getItem();

            // 1) contentId 모으기
            List<String> placeIdsReq = new ArrayList<>();
            List<String> courseIdsReq = new ArrayList<>();

            for (AreaBasedDto item : items) {
                if (COURSETYPE.equals(item.getCat1())) courseIdsReq.add(item.getContentid());
                else placeIdsReq.add(item.getContentid());
            }

            // 2) 한 번에 기존 것 조회
            Set<String> existsPlaceIds = new HashSet<>(placeRepository.findAllContentIdIn(placeIdsReq));
            Set<String> existsCourseIds = new HashSet<>(courseRepository.findAllContentIdIn(courseIdsReq));

            // 3) 새 것만 엔티티 빌드
            List<Place> savePlaces = new ArrayList<>();
            List<Course> saveCourses = new ArrayList<>();

            for (AreaBasedDto item : items) {
                if (!COURSETYPE.equals(item.getCat1())) {
                    if (existsPlaceIds.contains(item.getContentid())) continue;
                    savePlaces.add(
                            Place.builder()
                                    .address(item.getAddr1())
                                    .detailAddress(item.getAddr2())
                                    .sigunguCode(toIntOrNull(item.getSigungucode()))
                                    .category1(item.getCat1())
                                    .category2(item.getCat2())
                                    .category3(item.getCat3())
                                    .contentId(item.getContentid())
                                    .contentTypeId(item.getContenttypeid())
                                    .originImage(item.getFirstimage())
                                    .thumbnailImage(item.getFirstimage2())
                                    .mapx(toDoubleOrNull(item.getMapx()))
                                    .mapy(toDoubleOrNull(item.getMapy()))
                                    .tel(item.getTel())
                                    .title(item.getTitle())
                                    .build()
                    );
                } else {
                    if (existsCourseIds.contains(item.getContentid())) continue;
                    saveCourses.add(
                            Course.builder()
                                    .sigunguCode(toIntOrNull(item.getSigungucode()))
                                    .category1(item.getCat1())
                                    .category2(item.getCat2())
                                    .category3(item.getCat3())
                                    .contentId(item.getContentid())
                                    .originImage(item.getFirstimage())
                                    .thumbnailImage(item.getFirstimage2())
                                    .title(item.getTitle())
                                    .build()
                    );
                }
            }

            // 4) 배치로 저장
            saveInBatch(savePlaces, placeRepository);
            saveInBatch(saveCourses, courseRepository);
        }
    }

    private <T> void saveInBatch(List<T> list, CrudRepository<T, ?> repo) {
        final int BATCH = 100;
        for (int i = 0; i < list.size(); i += BATCH) {
            List<T> chunk = list.subList(i, Math.min(i + BATCH, list.size()));
            repo.saveAll(chunk);
        }
    }

    private Integer toIntOrNull(String s) {
        return (s == null || s.isBlank()) ? null : Integer.valueOf(s);
    }
    private Double toDoubleOrNull(String s) {
        return (s == null || s.isBlank()) ? null : Double.valueOf(s);
    }

}
