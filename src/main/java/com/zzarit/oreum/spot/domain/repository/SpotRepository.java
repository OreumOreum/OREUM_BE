package com.zzarit.oreum.spot.domain.repository;

import com.zzarit.oreum.member.domain.Member;
import com.zzarit.oreum.place.domain.Place;
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

    @Query("""
    SELECT COUNT(s) > 0
    FROM Spot s
    WHERE FUNCTION('MONTH', s.date) = :month
      AND FUNCTION('YEAR', s.date) = :year
      AND s.place = :place
""")
    boolean existsThisMonthWithPlace(@Param("year") int year,
                                     @Param("month") int month,
                                     @Param("place") Place place);

    @Query("""
    select s 
    from Spot s
    join s.visitLogs v
    where v.member = :member
      and year(s.date) = :year
    order by s.date desc, s.order asc
    """)
    List<Spot> findVisitedSpotsByMemberAndYear(
            @Param("member") Member member,
            @Param("year")   int year);

    List<Spot> Date(LocalDate date);
}