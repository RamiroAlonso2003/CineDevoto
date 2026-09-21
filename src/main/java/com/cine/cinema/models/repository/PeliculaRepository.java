package com.cine.cinema.models.repository;

import com.cine.cinema.models.entities.pelicula.Pelicula;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PeliculaRepository extends JpaRepository<Pelicula, Integer> {

    Optional<Pelicula> findByTituloIgnoreCase(String titulo);

    boolean existsByTituloIgnoreCase(String titulo);
}
