package com.cine.cinema.services;

import com.cine.cinema.mapper.PeliculaMapper;
import com.cine.cinema.models.entities.pelicula.Pelicula;
import com.cine.cinema.models.entities.pelicula.PeliculaDto;
import com.cine.cinema.models.entities.showtime.Showtime;
import com.cine.cinema.models.repository.PeliculaRepository;
import com.cine.cinema.models.repository.SalaRepository;
import com.cine.cinema.models.repository.ShowtimeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PeliculaService implements IPeliculaService {

    @Autowired
    private PeliculaRepository peliculaRepository;

    @Override
    public Long crearPelicula(PeliculaDto peliculaDTO){
        Pelicula pelicula = PeliculaMapper.fromDTO(peliculaDTO);

        Pelicula pelicula2 = peliculaRepository.save(pelicula);
        return pelicula2.getPeliculaId();
    };

    @Override
    public List<Pelicula> obtenerPeliculas() {
        return peliculaRepository.findAll();
    }


    @Override
    public Pelicula obtenerPeliculaPorId(Integer id){
        return peliculaRepository.findById(id).orElse(null);
    }

    @Override
    public void eliminarPeliculaPorId(Integer id){
        peliculaRepository.deleteById(id);
    }


    @Override
    public Pelicula actualizarPelicula(Integer id, PeliculaDto peliculaDTO) {
        Pelicula pelicula = peliculaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Pelicula inexistente"));

        // Actualiza los campos necesarios
        pelicula.setTitulo(peliculaDTO.getTitulo());
        pelicula.setDescripcion(peliculaDTO.getDescripcion());
        pelicula.setGenero(peliculaDTO.getGenero());
        pelicula.setPosterUrl(peliculaDTO.getPosterUrl());

        return peliculaRepository.save(pelicula);
    }
}
