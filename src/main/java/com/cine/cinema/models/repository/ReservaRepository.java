package com.cine.cinema.models.repository;

import com.cine.cinema.models.entities.reserva.Reserva;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReservaRepository extends JpaRepository<Reserva, Integer> {
    // Métodos de acceso a datos para Reserva
}
