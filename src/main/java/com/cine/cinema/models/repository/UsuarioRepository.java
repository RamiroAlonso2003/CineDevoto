package com.cine.cinema.models.repository;

import com.cine.cinema.models.entities.usuario.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuarioRepository extends JpaRepository<Usuario,Integer> {
    // Métodos de acceso a datos para Usuario
}
