package com.cine.cinema.mapper;

import com.cine.cinema.models.entities.showtime.Showtime;
import com.cine.cinema.models.entities.showtime.ShowtimeDto;

public class ShowtimeMapper {

    // ================================
    // Mapear Showtime -> ShowtimeDto
    // ================================
    public static ShowtimeDto toDto(Showtime showtime) {
        if (showtime == null) return null;

        return ShowtimeDto.builder()
                .inicio(showtime.getInicio())
                .fin(showtime.getFin())
                .peliculaId(Math.toIntExact(showtime.getPelicula().getPeliculaId()))
                .salaId(Math.toIntExact(showtime.getSala().getSalaId()))
                .build();
    }

    // ================================
    // Mapear ShowtimeDto -> Showtime
    // ================================
    public static Showtime fromDto(ShowtimeDto dto) {
        if (dto == null) return null;

        return Showtime.builder()
                .inicio(dto.getInicio())
                .fin(dto.getFin())
                // Relación con Pelicula y Sala se setea en el service
                .build();
    }
}
