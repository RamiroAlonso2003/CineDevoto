package com.cine.cinema.models.entities.showtime;

import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ShowtimeDto {

    private Integer showtimeId;
    private Integer peliculaId;
    private Integer salaId;
    private LocalDateTime inicio;
    private LocalDateTime fin;
}
