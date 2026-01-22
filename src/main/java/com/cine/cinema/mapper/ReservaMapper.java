package com.cine.cinema.mapper;

import com.cine.cinema.models.entities.reserva.Reserva;
import com.cine.cinema.models.entities.reserva.ReservaDto;
import com.cine.cinema.models.entities.usuario.Usuario;
import com.cine.cinema.models.entities.usuario.UsuarioDto;
import com.cine.cinema.models.entities.showtime.Showtime;

import org.springframework.stereotype.Component;

@Component
public class ReservaMapper {
    public ReservaDto toDto(Reserva reserva) {
        if (reserva == null) return null;
        ReservaDto dto = new ReservaDto();
        dto.setReservaId(reserva.getReservaId());
        dto.setUsuario(UsuarioMapper.toDto(reserva.getUsuario()));
        dto.setFechaReserva(reserva.getFechaReserva());
        dto.setEstado(reserva.getEstado());
        dto.setShowtime(reserva.getShowtime() != null ? com.cine.cinema.mapper.ShowtimeMapper.toDto(reserva.getShowtime()) : null);
        return dto;
    }

    public Reserva fromDto(ReservaDto dto) {
        if (dto == null) return null;
        return Reserva.builder()
            .reservaId(dto.getReservaId())
            .usuario(UsuarioMapper.fromDto(dto.getUsuario()))
            .fechaReserva(dto.getFechaReserva())
            .estado(dto.getEstado())
            .showtime(dto.getShowtime() != null ? com.cine.cinema.mapper.ShowtimeMapper.fromDto(dto.getShowtime()) : null)
            .build();
    }
}
