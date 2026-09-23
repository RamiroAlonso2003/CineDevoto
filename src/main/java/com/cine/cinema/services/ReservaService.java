
package com.cine.cinema.services;

import com.cine.cinema.models.entities.reserva.Reserva;
import com.cine.cinema.models.entities.reserva.ReservaDto;
import com.cine.cinema.mapper.ReservaMapper;
import com.cine.cinema.models.entities.showtime.AsientoReservado;
import com.cine.cinema.models.entities.showtime.Showtime;
import com.cine.cinema.models.entities.usuario.Usuario;
import com.cine.cinema.models.repository.ReservaRepository;
import com.cine.cinema.models.repository.ShowtimeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
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
    @Transactional
    public ReservaDto crearReserva(ReservaDto reservaDto) {
        Showtime showtime = showtimeRepository.findById(Long.valueOf(reservaDto.getShowtime().getShowtimeId()))
                .orElseThrow(() -> new RuntimeException("Showtime no encontrado"));

        Reserva reserva = Reserva.builder()
                .showtime(showtime)
                // El usuario de la reserva es siempre el autenticado (vía JWT), nunca
                // el que venga en el body: si no, cualquiera podría reservar "como" otro.
                .usuario(usuarioAutenticado())
                .fechaReserva(LocalDateTime.now())
                .estado("CONFIRMADA")
                .build();
        Reserva guardada = reservaRepository.save(reserva);

        if (reservaDto.getAsientos() != null) {
            for (var asientoDto : reservaDto.getAsientos()) {
                AsientoReservado asiento = showtime.reservarAsiento(asientoDto.getFila(), asientoDto.getNumero());
                asiento.asignarReserva(guardada);
            }
        }

        return reservaMapper.toDto(guardada);
    }

    private Usuario usuarioAutenticado() {
        String email = SecurityContextHolder.getContext().getAuthentication().getName();
        return usuarioService.findByEmail(email);
    }

    @Override
    public List<ReservaDto> listarReservas() {
        return reservaRepository.findByUsuario(usuarioAutenticado()).stream()
                .map(reservaMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public ReservaDto obtenerReserva(Long id) {
        Reserva reserva = reservaRepository.findById(id.intValue())
                .orElseThrow(() -> new RuntimeException("Reserva no encontrada"));
        validarDueño(reserva);
        return reservaMapper.toDto(reserva);
    }

    @Override
    @Transactional
    public void cancelarReserva(Long id) {
        Reserva reserva = reservaRepository.findById(id.intValue())
                .orElseThrow(() -> new RuntimeException("Reserva no encontrada"));
        validarDueño(reserva);

        reserva.getShowtime().liberarAsientosDe(reserva);
        reserva.setEstado("CANCELADA");
        reservaRepository.save(reserva);
    }

    private void validarDueño(Reserva reserva) {
        if (!reserva.getUsuario().equals(usuarioAutenticado())) {
            throw new IllegalStateException("No podés operar sobre una reserva que no es tuya");
        }
    }
}

