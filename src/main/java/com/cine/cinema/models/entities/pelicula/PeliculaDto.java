package com.cine.cinema.models.entities.pelicula;

import lombok.Data;
import com.cine.cinema.models.entities.genero.Genero;

@Data
@lombok.Builder
public class PeliculaDto {
    private Long peliculaId;
    private String titulo;
    private String descripcion;
    private String posterUrl;
    private Integer duracion;
    private Genero genero;
}