package com.cine.cinema.mapper;

import com.cine.cinema.models.entities.pelicula.Pelicula;
import com.cine.cinema.models.entities.pelicula.PeliculaDto;

public class PeliculaMapper {
    public static Pelicula fromDTO(PeliculaDto dto) {
        return Pelicula.builder()
                .peliculaId(dto.getPeliculaId())
                .titulo(dto.getTitulo())
                .descripcion(dto.getDescripcion())
                .genero(dto.getGenero())
                .duracion(dto.getDuracion())
                .posterUrl(dto.getPosterUrl())
                .build();
    }

    public static PeliculaDto toDTO(Pelicula pelicula) {
        return PeliculaDto.builder()
                .peliculaId(pelicula.getPeliculaId())
                .titulo(pelicula.getTitulo())
                .descripcion(pelicula.getDescripcion())
                .genero(pelicula.getGenero())
                .duracion(pelicula.getDuracion())
                .posterUrl(pelicula.getPosterUrl())
                .build();
    }
}
