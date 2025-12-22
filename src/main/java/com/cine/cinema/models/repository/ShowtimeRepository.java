package com.cine.cinema.models.repository;

import com.cine.cinema.models.entities.showtime.Showtime;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import org.springframework.stereotype.Repository;

@Repository
public interface ShowtimeRepository extends JpaRepository<Showtime, Long>, JpaSpecificationExecutor<Showtime> {

}
