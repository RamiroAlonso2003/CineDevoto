package com.cine.cinema.services;

import com.cine.cinema.models.entities.showtime.Showtime;
import com.cine.cinema.models.entities.showtime.ShowtimeDto;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface IshowtimeService {
    List<Showtime> traerShowtimes(Integer peliculaId, LocalDate fecha);
    List<Showtime> findAll();
    Optional<Showtime> findById(Integer id);
    Showtime save(ShowtimeDto showtimeDto);
    void deleteById(Integer id);
}
