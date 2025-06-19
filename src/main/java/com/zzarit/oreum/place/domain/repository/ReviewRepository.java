package com.zzarit.oreum.place.domain.repository;

import com.zzarit.oreum.place.domain.Review;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReviewRepository extends JpaRepository<Review, Long> {
}