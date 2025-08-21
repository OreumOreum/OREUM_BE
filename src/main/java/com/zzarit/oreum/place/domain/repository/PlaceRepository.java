package com.zzarit.oreum.place.domain.repository;

import com.querydsl.core.BooleanBuilder;
import com.zzarit.oreum.member.domain.Category;
import com.zzarit.oreum.member.domain.QCategory;
import com.zzarit.oreum.member.domain.Type;
import com.zzarit.oreum.place.domain.Place;
import com.zzarit.oreum.place.domain.QPlace;
import com.zzarit.oreum.place.domain.QPlaceCategory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

public interface PlaceRepository extends JpaRepository<Place, Long>, PlaceRepositoryCustom {
    List<Place> findBySigunguCode(Integer sigunguCode);
    List<Place> findBySigunguCodeAndIdNotIn(Integer sigunguCode, List<Long> ids);
    Page<Place> findPlaceList(String contentTypeId, Integer sigunguCode, Type type,Pageable pageable);

    @Query("select p.contentId from Place p where p.contentId in :ids")
    List<String> findAllContentIdIn(@Param("ids") Collection<String> ids);

    Optional<Place> findByContentId(String contentId);

    @Query("""
        SELECT p
        FROM Place p
        JOIN p.placeCategories pc
        WHERE pc.category      = :category
          AND p.contentTypeId  = :contentTypeId
          AND p.originImage IS NOT NULL
        ORDER BY function('RAND')
        """)
    List<Place> findFirstRandomByCategoryAndContentType(
            @Param("category") Category category,
            @Param("contentTypeId") String contentTypeId,
            Pageable pageable
    );

    default Optional<Place> findOneRandomByCategoryAndContentType(
            Category category,
            String contentTypeId
    ) {
        List<Place> list = findFirstRandomByCategoryAndContentType(
                category, contentTypeId, PageRequest.of(0, 1)
        );
        return list.stream().findFirst();
    }

    List<Place> findAllByContentIdIn(Collection<String> contentIds);
}