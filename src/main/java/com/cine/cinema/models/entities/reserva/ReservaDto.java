package com.cine.cinema.models.entities.reserva;

import com.cine.cinema.models.entities.showtime.Showtime;
import com.cine.cinema.models.entities.showtime.ShowtimeDto;
import lombok.Data;
import java.time.LocalDateTime;
import com.cine.cinema.models.entities.usuario.UsuarioDto;
import java.util.List;
import com.cine.cinema.models.entities.showtime.AsientoDto;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

@Data
public class ReservaDto {
    private Long reservaId;
    private UsuarioDto usuario;
    private LocalDateTime fechaReserva;
    private String estado;
    private List<AsientoDto> asientos;
    private ShowtimeDto showtime ;
}