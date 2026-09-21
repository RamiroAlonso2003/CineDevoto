
package com.cine.cinema.services;

import com.cine.cinema.models.entities.reserva.Reserva;
import com.cine.cinema.models.entities.reserva.ReservaDto;
import com.cine.cinema.mapper.ReservaMapper;
import com.cine.cinema.models.entities.showtime.Showtime;
import com.cine.cinema.models.entities.usuario.Usuario;
import com.cine.cinema.models.repository.ReservaRepository;
import com.cine.cinema.models.repository.ShowtimeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ReservaService implements IReservaService {
    @Autowired
    private ReservaRepository reservaRepository;
    @Autowired
    private ReservaMapper reservaMapper;
    @Autowired
    private ShowtimeRepository showtimeRepository;
    @Autowired
    private IUsuarioService usuarioService;

    @Override
    public ReservaDto crearReserva(ReservaDto reservaDto) {
        Showtime showtime = showtimeRepository.findById(Long.valueOf(reservaDto.getShowtime().getShowtimeId()))
                .orElseThrow(() -> new RuntimeException("Showtime no encontrado"));
        Reserva reserva = reservaMapper.fromDto(reservaDto);
        reserva.setShowtime(showtime);
        // El usuario de la reserva es siempre el autenticado (vía JWT), nunca
        // el que venga en el body: si no, cualquiera podría reservar "como" otro.
        reserva.setUsuario(usuarioAutenticado());

        // Reservar los asientos solicitados
        if (reservaDto.getAsientos() != null) {
            for (var asiento : reservaDto.getAsientos()) {
                // Se asume que reservarAsiento acepta fila y número
               showtime.reservarAsiento(asiento.getFila(), asiento.getNumero());
            }
        }

        Reserva guardada = reservaRepository.save(reserva);
        return reservaMapper.toDto(guardada);
    }

    private Usuario usuarioAutenticado() {
        String email = SecurityContextHolder.getContext().getAuthentication().getName();
        return usuarioService.findByEmail(email);
    }

    @Override
    public List<ReservaDto> listarReservas() {
        return reservaRepository.findAll().stream()
                .map(reservaMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public ReservaDto obtenerReserva(Long id) {
        Reserva reserva = reservaRepository.findById(id.intValue())
                .orElseThrow(() -> new RuntimeException("Reserva no encontrada"));
        return reservaMapper.toDto(reserva);
    }

    @Override
    public void cancelarReserva(Long id) {
        Reserva reserva = reservaRepository.findById(id.intValue())
                .orElseThrow(() -> new RuntimeException("Reserva no encontrada"));
        reserva.setEstado("CANCELADA");
        reservaRepository.save(reserva);
    }
}

