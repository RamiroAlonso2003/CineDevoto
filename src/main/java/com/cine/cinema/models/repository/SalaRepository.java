package com.cine.cinema.models.repository;

import com.cine.cinema.models.entities.sala.Sala;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SalaRepository extends JpaRepository<Sala, Integer> {
    // Métodos de acceso a datos para Sala
}
