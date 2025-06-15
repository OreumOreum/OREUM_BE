package com.oreum.zzarit.place.domain.repository;

import com.oreum.zzarit.place.domain.Rating;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RatingRepository extends JpaRepository<Rating, Long> {
}