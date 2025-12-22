package com.cine.cinema.controllers;

import com.cine.cinema.models.entities.reserva.ReservaDto;
import com.cine.cinema.services.IReservaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/reservas")
public class ReservaController {
    @Autowired
    private IReservaService reservaService;

    @PostMapping
    @ResponseStatus(org.springframework.http.HttpStatus.CREATED)
    public ReservaDto crearReserva(@RequestBody ReservaDto reservaDto) {
        return reservaService.crearReserva(reservaDto);
    }


    @GetMapping
    @ResponseStatus(org.springframework.http.HttpStatus.OK)
    public List<ReservaDto> listarReservas() {
        return reservaService.listarReservas();
    }


    @GetMapping("/{id}")
    @ResponseStatus(org.springframework.http.HttpStatus.OK)
    public ReservaDto obtenerReserva(@PathVariable Long id) {
        return reservaService.obtenerReserva(id);
    }

    @PatchMapping("/{id}/cancelar")
    @ResponseStatus(org.springframework.http.HttpStatus.OK)
    public void cancelarReserva(@PathVariable Long id) {
        reservaService.cancelarReserva(id);
    }
}
