package com.cine.cinema.controllers;

import com.cine.cinema.models.entities.showtime.Showtime;
import com.cine.cinema.models.entities.showtime.ShowtimeDto;

import com.cine.cinema.services.ShowtimeService;

import com.cine.cinema.mapper.ShowtimeMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/showtimes")
public class ShowtimeController {
    private static final Logger logger = LoggerFactory.getLogger(ShowtimeController.class);

    private final ShowtimeService showtimeService;


    public ShowtimeController(ShowtimeService showtimeService) {
        this.showtimeService = showtimeService;
    }
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Integer crearShowtime(@RequestBody ShowtimeDto showtimeDto) {
        logger.info("Solicitud para crear un nuevo showtime");
        return showtimeService.save(showtimeDto).getShowtimeId();
    }

    @GetMapping("/{id}")
    public ShowtimeDto obtenerShowtimePorId(@PathVariable Integer id) {
        logger.info("Solicitud para obtener el showtime con id {}", id);
        return showtimeService.findById(id)
                .map(ShowtimeMapper::toDto)
                .orElse(null);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void eliminarShowtime(@PathVariable Integer id) {
        logger.info("Solicitud para eliminar el showtime con id {}", id);
        showtimeService.deleteById(id);
    }

    @GetMapping
    public List<ShowtimeDto> obtenerShowtimes(
            @RequestParam(required = false) Integer peliculaId,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate fecha
    ) {
        logger.info("Solicitud para obtener showtimes filtrando por peliculaId={} y fecha={}", peliculaId, fecha);
        return showtimeService.traerShowtimes(peliculaId, fecha)
                .stream()
                .map(ShowtimeMapper::toDto)
                .toList();
    }
}
