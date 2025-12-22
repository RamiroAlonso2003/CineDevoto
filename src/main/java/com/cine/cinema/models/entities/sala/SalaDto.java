package com.cine.cinema.models.entities.sala;

import lombok.Data;

@Data
public class SalaDto {
    private Long salaId;
    private Integer filas;
    private Integer asientosPorFila;
}