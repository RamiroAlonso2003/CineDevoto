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
        dto.setUsuario(toUsuarioDto(reserva.getUsuario()));
        dto.setFechaReserva(reserva.getFechaReserva());
        dto.setEstado(reserva.getEstado());
        // Puedes agregar showtime si lo necesitas en el DTO
        return dto;
    }

    public Reserva fromDto(ReservaDto dto) {
        if (dto == null) return null;
        Reserva reserva = new Reserva();
        reserva.setReservaId(dto.getReservaId());
        reserva.setUsuario(toUsuario(dto.getUsuario()));
        reserva.setFechaReserva(dto.getFechaReserva());
        reserva.setEstado(dto.getEstado());
        // Puedes agregar showtime si lo necesitas en la entidad
        return reserva;
    }

    private UsuarioDto toUsuarioDto(Usuario usuario) {
        if (usuario == null) return null;
        UsuarioDto dto = new UsuarioDto();
        dto.setUsuarioId(usuario.getUsuarioId());
        dto.setEmail(usuario.getEmail());
        dto.setNombre(usuario.getNombre());
        dto.setKeycloakId(usuario.getKeycloakId());
        return dto;
    }

    private Usuario fromUsuarioDto(UsuarioDto dto) {
        if (dto == null) return null;
        Usuario usuario = new Usuario();
        usuario.setUsuarioId(dto.getUsuarioId());
        usuario.setEmail(dto.getEmail());
        usuario.setNombre(dto.getNombre());
        usuario.setKeycloakId(dto.getKeycloakId());
        return usuario;
    }
}
