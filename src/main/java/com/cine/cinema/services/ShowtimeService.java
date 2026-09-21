package com.cine.cinema.services;

import com.cine.cinema.models.entities.pelicula.Pelicula;
import com.cine.cinema.models.entities.sala.Sala;
import com.cine.cinema.models.entities.showtime.AsientoEstadoDto;
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
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class ShowtimeService implements IshowtimeService {
    @Autowired
    private ShowtimeRepository showtimeRepository;
    @Autowired
    private PeliculaRepository peliculaRepository;
    @Autowired
    private SalaRepository salaRepository;
    @Autowired
    private com.cine.cinema.models.repository.UsuarioRepository usuarioRepository;


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

        boolean solapa = showtimeRepository.existsSolapamiento(
                sala.getSalaId(),
                showtimeDto.getInicio(),
                showtimeDto.getFin(),
                showtimeDto.getShowtimeId()
        );
        if (solapa) {
            throw new IllegalStateException("La sala ya tiene una función en ese horario");
        }

        Showtime showtime = Showtime.builder()
                .pelicula(pelicula)
                .inicio(showtimeDto.getInicio())
                .fin(showtimeDto.getFin())
                .sala(sala)
                .build();

        return showtimeRepository.save(showtime);
    }

    public List<AsientoEstadoDto> obtenerMapaAsientos(Integer showtimeId) {
        Showtime showtime = showtimeRepository.findById(Long.valueOf(showtimeId))
                .orElseThrow(() -> new RuntimeException("Showtime no encontrado"));
        Sala sala = showtime.getSala();

        Set<String> ocupados = showtime.getAsientosReservados().stream()
                .map(a -> a.getFila() + a.getNumero())
                .collect(Collectors.toSet());

        List<AsientoEstadoDto> mapa = new ArrayList<>();
        for (int f = 0; f < sala.getFilas(); f++) {
            String fila = String.valueOf((char) ('A' + f));
            for (int n = 1; n <= sala.getAsientosPorFila(); n++) {
                boolean ocupado = ocupados.contains(fila + n);
                mapa.add(new AsientoEstadoDto(fila, n, ocupado ? "OCUPADO" : "DISPONIBLE"));
            }
        }
        return mapa;
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
