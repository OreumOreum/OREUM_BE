package com.zzarit.oreum.spot.domain.repository;

import com.zzarit.oreum.member.domain.Category;
import com.zzarit.oreum.spot.domain.Spot;
import com.zzarit.oreum.spot.domain.SpotCategorySummary;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface SpotCategorySummaryRepository extends JpaRepository<SpotCategorySummary, Long> {
    Optional<SpotCategorySummary> findBySpotAndCategory(Spot spot, Category category);
    List<SpotCategorySummary> findAllBySpotIdOrderByVisitCountDesc(Long spotId);
}