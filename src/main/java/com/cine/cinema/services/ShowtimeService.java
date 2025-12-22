package com.cine.cinema.services;

import com.cine.cinema.models.entities.pelicula.Pelicula;
import com.cine.cinema.models.entities.sala.Sala;
import com.cine.cinema.models.entities.showtime.Showtime;
import com.cine.cinema.models.entities.showtime.ShowtimeDto;
import com.cine.cinema.models.repository.PeliculaRepository;
import com.cine.cinema.models.repository.SalaRepository;
import com.cine.cinema.models.repository.ShowtimeRepository;
import com.cine.cinema.models.repository.ShowtimeSpecifications;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Service
public class ShowtimeService implements IshowtimeService {
    @Autowired
    private ShowtimeRepository showtimeRepository;
    private PeliculaRepository peliculaRepository;
    private SalaRepository salaRepository;


    @Override
    public List<Showtime> findAll() {
        return showtimeRepository.findAll();
    }


    @Override
    public Optional<Showtime> findById(Integer id) {
        return showtimeRepository.findById(Long.valueOf(id));
    }


    @Override
    public Showtime save(ShowtimeDto showtimeDto) {

        Pelicula pelicula = peliculaRepository
                .findById(showtimeDto.getPeliculaId())
                .orElseThrow(() ->
                        new RuntimeException("Pelicula inexistente")
                );

        Sala sala = salaRepository
                .findById(showtimeDto.getSalaId())
                .orElseThrow(() ->
                        new RuntimeException("Sala inexistente")
                );

        Showtime showtime = Showtime.builder()
                .pelicula(pelicula)
                .inicio(showtimeDto.getInicio())
                .fin(showtimeDto.getInicio())
                .sala(sala)
                .build();

        return showtimeRepository.save(showtime);
    }


    @Override
    public void deleteById(Integer id) {
        showtimeRepository.deleteById(Long.valueOf(id));
    }

    @Override
    public List<Showtime> traerShowtimes(Integer peliculaId, LocalDate fecha) {

        Specification<Showtime> spec = Specification
                .where(ShowtimeSpecifications.conPelicula(peliculaId))
                .and(ShowtimeSpecifications.conDiaInicio(fecha));

        return showtimeRepository.findAll(spec);
    }
}
