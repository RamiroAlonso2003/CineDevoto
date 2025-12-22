package com.cine.cinema.services;

import com.cine.cinema.models.entities.usuario.Usuario;
import java.util.List;

public interface UsuarioService {
    List<Usuario> findAll();
    Usuario findById(Long id);
    Usuario save(Usuario usuario);
    void deleteById(Long id);
}
