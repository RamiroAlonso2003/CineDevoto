package com.cine.cinema.models.repository;

import com.cine.cinema.models.entities.reserva.Reserva;
import com.cine.cinema.models.entities.usuario.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReservaRepository extends JpaRepository<Reserva, Integer> {

    List<Reserva> findByUsuario(Usuario usuario);
}
