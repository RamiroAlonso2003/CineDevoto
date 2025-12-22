package com.cine.cinema.services;

import com.cine.cinema.models.entities.reserva.ReservaDto;
import java.util.List;

public interface IReservaService {
    ReservaDto crearReserva(ReservaDto reservaDto);
    List<ReservaDto> listarReservas();
    ReservaDto obtenerReserva(Long id);
    void cancelarReserva(Long id);
}
