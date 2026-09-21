package com.cine.cinema.models.repository;

import com.cine.cinema.models.entities.showtime.Showtime;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;

@Repository
public interface ShowtimeRepository extends JpaRepository<Showtime, Long>, JpaSpecificationExecutor<Showtime> {

    @Query("""
        SELECT COUNT(s) > 0 FROM Showtime s
        WHERE s.sala.salaId = :salaId
          AND (:excludeId IS NULL OR s.showtimeId <> :excludeId)
          AND s.inicio < :fin
          AND s.fin > :inicio
    """)
    boolean existsSolapamiento(@Param("salaId") Long salaId,
                                @Param("inicio") LocalDateTime inicio,
                                @Param("fin") LocalDateTime fin,
                                @Param("excludeId") Integer excludeId);
}
