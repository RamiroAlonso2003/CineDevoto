package com.cine.cinema.models.repository;

import com.cine.cinema.models.entities.pelicula.Pelicula;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PeliculaRepository extends JpaRepository<Pelicula, Integer> {
    // Métodos de acceso a datos para Pelicula
}
