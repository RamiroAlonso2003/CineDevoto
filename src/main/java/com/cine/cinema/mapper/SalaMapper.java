package com.cine.cinema.mapper;

import com.cine.cinema.models.entities.sala.Sala;
import com.cine.cinema.models.entities.sala.SalaDto;

public class SalaMapper {
    public static Sala fromDTO(SalaDto dto) {
        return Sala.builder()
                .salaId(dto.getSalaId())
                .filas(dto.getFilas())
                .asientosPorFila(dto.getAsientosPorFila())
                .build();
    }

    public static SalaDto toDTO(Sala sala) {
        return SalaDto.builder()
                .salaId(sala.getSalaId())
                .filas(sala.getFilas())
                .asientosPorFila(sala.getAsientosPorFila())
                .build();
    }
}
