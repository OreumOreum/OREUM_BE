package com.zzarit.oreum.scheduler.service;

import com.zzarit.oreum.member.domain.Category;
import com.zzarit.oreum.place.domain.*;
import com.zzarit.oreum.place.domain.repository.CategoryMapRepository;
import com.zzarit.oreum.place.domain.repository.CourseCategoryRepository;
import com.zzarit.oreum.place.domain.repository.CourseRepository;
import com.zzarit.oreum.place.domain.repository.PlaceCategoryRepository;
import com.zzarit.oreum.place.domain.repository.PlaceRepository;
import com.zzarit.oreum.scheduler.client.OpenApiClient;
import com.zzarit.oreum.scheduler.client.dto.AreaBasedDto;
import com.zzarit.oreum.scheduler.client.dto.DetailCommonDto;
import com.zzarit.oreum.scheduler.client.dto.OpenApiResponseDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.repository.CrudRepository;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor
public class SynchronizeService {
    private final OpenApiClient openApiClient;
    private final PlaceRepository placeRepository;
    private final CourseRepository courseRepository;
    private final CategoryMapRepository categoryMapRepository;
    private final PlaceCategoryRepository placeCategoryRepository;
    private final CourseCategoryRepository courseCategoryRepository;
    private static final String COURSETYPE = "C01";
    private int placeOverviewOffset = 0;
    private int courseOverviewOffset = 0;

    public void initialize(){
        savePlaceAndCourse();
        saveCategoryMap();
    }


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

            // 2) 기존 ID 조회
            Set<String> existing = new HashSet<>();
            existing.addAll(placeRepository.findAllContentIdIn(placeIdsReq));
            existing.addAll(courseRepository.findAllContentIdIn(courseIdsReq));

            // 3) 신규 엔티티 빌드
            List<Place> newPlaces = new ArrayList<>();
            List<Course> newCourses = new ArrayList<>();

            for (AreaBasedDto item : items) {
                String cid = item.getContentid();
                if (existing.contains(cid)) continue;


                if (!COURSETYPE.equals(item.getCat1())) {
                    // Place 엔티티 생성
                    Place place = Place.builder()
                            .address(item.getAddr1())
                            .detailAddress(item.getAddr2())
                            .sigunguCode(toIntOrNull(item.getSigungucode()))
                            .category1(item.getCat1())
                            .category2(item.getCat2())
                            .category3(item.getCat3())
                            .contentId(cid)
                            .contentTypeId(item.getContenttypeid())
                            .originImage(item.getFirstimage())
                            .thumbnailImage(item.getFirstimage2())
                            .mapx(toDoubleOrNull(item.getMapx()))
                            .mapy(toDoubleOrNull(item.getMapy()))
                            .tel(item.getTel())
                            .title(item.getTitle())
                            .build();
                    newPlaces.add(place);
                } else {
                    // Course 엔티티 생성
                    Course course = Course.builder()
                            .sigunguCode(toIntOrNull(item.getSigungucode()))
                            .category1(item.getCat1())
                            .category2(item.getCat2())
                            .category3(item.getCat3())
                            .contentId(cid)
                            .originImage(item.getFirstimage())
                            .thumbnailImage(item.getFirstimage2())
                            .title(item.getTitle())
                            .build();
                    newCourses.add(course);

                }
            }

            // 4) 배치 저장
            saveInBatch(newPlaces, placeRepository);
            saveInBatch(newCourses, courseRepository);
        }
    }

    // Place,Course를 소분류에 따라 오름오름 유형에 매핑
    @Transactional
    public void saveCategoryMap() {
        // 기존 매핑 제거
        placeCategoryRepository.deleteAll();
        courseCategoryRepository.deleteAll();

        // SubCategory별 매핑 로드
        Map<String, List<Category>> categoriesBySub = categoryMapRepository.findAll().stream()
                .collect(Collectors.groupingBy(
                        cm -> cm.getId().getCategory3(),
                        Collectors.mapping(CategoryMap::getCategoryType, Collectors.toList())
                ));

        List<PlaceCategory> placeCats = new ArrayList<>();
        List<CourseCategory> courseCats = new ArrayList<>();

        // 모든 Place에 대해 매핑 적용
        for (Place place : placeRepository.findAll()) {
            List<Category> cats = categoriesBySub.getOrDefault(place.getCategory3(), Collections.emptyList());
            for (Category cat : cats) {
                placeCats.add(new PlaceCategory(place, cat));
            }
        }

        // 모든 Course에 대해 매핑 적용
        for (Course course : courseRepository.findAll()) {
            List<Category> cats = categoriesBySub.getOrDefault(course.getCategory3(), Collections.emptyList());
            for (Category cat : cats) {
                courseCats.add(new CourseCategory(course, cat));
            }
        }

        placeCategoryRepository.saveAll(placeCats);
        courseCategoryRepository.saveAll(courseCats);
    }

    /**
     * 매일 낮 12시 500건씩 Place/Course Overview 갱신
     */
    @Scheduled(cron = "0 0 12 * * ?")
    public void saveOverviewDaily() {
        saveOverviewBatch();
    }

    @Transactional
    public void saveOverviewBatch() {
        int batchSize = 500;

        List<Place> places = placeRepository.findAll();
        int pTo = Math.min(placeOverviewOffset + batchSize, places.size());
        List<Place> subPlaces = places.subList(placeOverviewOffset, pTo);
        for (Place place : subPlaces) {
            DetailCommonDto dto = openApiClient.getDetailCommon(place.getContentId());
            if (dto == null) {
                continue;
            }
            place.setOverview(dto.getOverview());
        }
        placeRepository.saveAll(subPlaces);
        placeOverviewOffset = (pTo >= places.size()) ? 0 : pTo;

        List<Course> courses = courseRepository.findAll();
        int cTo = Math.min(courseOverviewOffset + batchSize, courses.size());
        List<Course> subCourses = courses.subList(courseOverviewOffset, cTo);
        for (Course course : subCourses) {
            DetailCommonDto dto = openApiClient.getDetailCommon(course.getContentId());
            if (dto == null) {
                continue;
            }
            course.setOverview(dto.getOverview());
        }
        courseRepository.saveAll(subCourses);
        courseOverviewOffset = (cTo >= courses.size()) ? 0 : cTo;
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
