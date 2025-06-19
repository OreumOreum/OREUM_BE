package com.zzarit.oreum.place.domain.repository;

import com.zzarit.oreum.place.domain.Rating;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RatingRepository extends JpaRepository<Rating, Long> {
}