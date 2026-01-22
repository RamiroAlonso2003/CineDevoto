package com.cine.cinema.models.entities.sala;

import lombok.Data;
import lombok.Builder;

@Data
@Builder
public class SalaDto {
    private Long salaId;
    private Integer filas;
    private Integer asientosPorFila;
}