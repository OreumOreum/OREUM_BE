package com.zzarit.oreum.place.domain.repository;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.zzarit.oreum.member.domain.QCategory;
import com.zzarit.oreum.member.domain.Type;
import com.zzarit.oreum.place.domain.Place;
import com.zzarit.oreum.place.domain.QPlace;
import com.zzarit.oreum.place.domain.QPlaceCategory;
import com.zzarit.oreum.place.service.dto.PlaceSearchConditionDto;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class PlaceRepositoryImpl implements PlaceRepositoryCustom {

    private final JPAQueryFactory queryFactory;

    @Override
    public Page<Place> searchPlaces(PlaceSearchConditionDto condition, Pageable pageable, Integer sigunguCode) {
        QPlace place = QPlace.place;

        BooleanBuilder builder = new BooleanBuilder();

        if (StringUtils.hasText(condition.keyword())) {
            builder.or(place.title.containsIgnoreCase(condition.keyword()));
            builder.or(place.address.containsIgnoreCase(condition.keyword()));
            builder.or(place.tel.containsIgnoreCase(condition.keyword()));
        }

        if (sigunguCode != null) {
            builder.and(place.sigunguCode.eq(sigunguCode));
        }

        List<Place> content = queryFactory
                .selectFrom(place)
                .where(builder)
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();

        Long total = Optional.ofNullable(
                queryFactory
                        .select(place.count())
                        .from(place)
                        .where(builder)
                        .fetchOne()
        ).orElse(0L);

        return new PageImpl<>(content, pageable, total);
    }

    @Override
    public Page<Place> findPlaceList(Integer sigunguCode, Type type, Pageable pageable) {
        QPlace place = QPlace.place;
        QPlaceCategory placeCategory = QPlaceCategory.placeCategory;
        QCategory category = QCategory.category;

        BooleanBuilder builder = new BooleanBuilder();

        if (sigunguCode != null) {
            builder.and(place.sigunguCode.eq(sigunguCode));
        }
        if (type != null) {
            builder.and(category.type.eq(type));
        }

        List<Place> content = queryFactory
                .selectDistinct(place)
                .from(place)
                .join(place.placeCategories, placeCategory)
                .join(placeCategory.category, category)
                .where(builder)
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();

        long count = queryFactory
                .selectFrom(place)
                .join(place.placeCategories, placeCategory)
                .join(placeCategory.category, category)
                .where(builder)
                .fetchCount();

        return new PageImpl<>(content, pageable, count);
    }
}