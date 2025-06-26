package com.zzarit.oreum.spot.domain.repository;

import com.zzarit.oreum.spot.domain.Spot;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;

public interface SpotRepository extends JpaRepository<Spot, Long> {
    boolean existsByDate(LocalDate date);
    List<Spot> findAllByDateBetween(LocalDate startDate, LocalDate endDate);
    @Query("SELECT s FROM Spot s JOIN FETCH s.place WHERE s.date = :date")
    List<Spot> findAllByDateWithPlace(@Param("date") LocalDate date);
    @Query("select s.place.id from Spot s")
    List<Long> findAllPlaceIds();
}