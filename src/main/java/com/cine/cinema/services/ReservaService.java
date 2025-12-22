
package com.cine.cinema.services;

import com.cine.cinema.models.entities.reserva.Reserva;
import com.cine.cinema.models.entities.reserva.ReservaDto;
import com.cine.cinema.mapper.ReservaMapper;
import com.cine.cinema.models.repository.ReservaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ReservaService implements IReservaService {
    @Autowired
    private ReservaRepository reservaRepository;
    private ReservaMapper reservaMapper;
    private ShowtimeRepository showtimeRepository;
    @Autowired
    private UsuarioRepository usuarioRepository;

    @Override
    public ReservaDto crearReserva(ReservaDto reservaDto) {
        Showtime showtime = showtimeRepository.findById(reservaDto.getShowtime().getShowtimeId())
                .orElseThrow(() -> new RuntimeException("Showtime no encontrado"));
        Reserva reserva = reservaMapper.fromDto(reservaDto);
        reserva.setShowtime(showtime);
        reserva.setUsuario(findOrCreateUsuario(reservaDto.getUsuario()));

        // Reservar los asientos solicitados
        if (reservaDto.getAsientos() != null) {
            for (var asiento : reservaDto.getAsientos()) {
                // Se asume que reservarAsiento acepta fila y número
                reservarAsientoEnShowtime(showtime, asiento.getFila(), asiento.getNumero());
            }
        }

        Reserva guardada = reservaRepository.save(reserva);
        return reservaMapper.toDto(guardada);
    }

    private void reservarAsientoEnShowtime(Showtime showtime, String fila, Integer numero) {
        // Aquí puedes adaptar la lógica según la implementación de reservarAsiento
        // Si tu método reservarAsiento solo acepta número, puedes concatenar fila y número o modificar el método
        // Ejemplo: showtime.reservarAsiento(fila, numero);
        // Si solo acepta número:
        // showtime.reservarAsiento(numero);
        // Aquí se asume que puedes modificar Showtime para aceptar fila y número
        // showtime.reservarAsiento(fila, numero);
        // Por ahora, solo ejemplo:
        // showtime.reservarAsiento(numero);
        // Si tienes que crear el AsientoReservado manualmente:
        // ...
        // Debes adaptar esto según tu dominio
    }
    }

    Usuario findOrCreateUsuario(UsuarioDto usuarioDto) {

        return usuarioRepository.findById(usuarioDto.getUsuarioId().intValue())
                .orElseGet(() -> usuarioRepository.save(usuarioMapper.fromDto(usuarioDto)));
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
    public void eliminarReserva(Long id) {
        reservaRepository.deleteById(id.intValue());
    }

    @Override
    public void cancelarReserva(Long id) {
        Reserva reserva = reservaRepository.findById(id.intValue())
                .orElseThrow(() -> new RuntimeException("Reserva no encontrada"));
        reserva.setEstado("CANCELADA");
        reservaRepository.save(reserva);
    }
}
