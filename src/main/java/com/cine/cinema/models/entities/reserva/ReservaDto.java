package com.cine.cinema.models.entities.reserva;

import lombok.Data;
import java.time.LocalDateTime;
import com.cine.cinema.models.entities.usuario.UsuarioDto;
import java.util.List;
import com.cine.cinema.models.entities.showtime.AsientoDto;

@Data
public class ReservaDto {
    private Long reservaId;
    private UsuarioDto usuario;
    private LocalDateTime fechaReserva;
    private String estado;
    private List<AsientoDto> asientos;
}