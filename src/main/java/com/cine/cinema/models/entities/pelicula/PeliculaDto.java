package com.cine.cinema.models.entities.pelicula;

import lombok.Data;
import com.cine.cinema.models.entities.genero.Genero;

@Data
public class PeliculaDto {
    private Long peliculaId;
    private String titulo;
    private String descripcion;
    private String posterUrl;
    private Genero genero;
}