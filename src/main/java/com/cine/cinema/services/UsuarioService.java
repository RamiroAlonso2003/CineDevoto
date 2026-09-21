package com.cine.cinema.services;

import com.cine.cinema.models.entities.usuario.Usuario;
import com.cine.cinema.models.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UsuarioService implements IUsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public List<Usuario> findAll() {
        return usuarioRepository.findAll();
    }

    @Override
    public Usuario findById(Long id) {
        return usuarioRepository.findById(id.intValue())
                .orElseThrow(() -> new RuntimeException("Usuario inexistente"));
    }

    @Override
    public Usuario findByEmail(String email) {
        return usuarioRepository.findByEmailIgnoreCase(email)
                .orElseThrow(() -> new RuntimeException("Usuario inexistente"));
    }

    @Override
    public Usuario registrar(Usuario usuario) {
        if (usuarioRepository.existsByEmailIgnoreCase(usuario.getEmail())) {
            throw new IllegalStateException("Ya existe un usuario con ese email");
        }
        usuario.setPassword(passwordEncoder.encode(usuario.getPassword()));
        return usuarioRepository.save(usuario);
    }

    @Override
    public void deleteById(Long id) {
        usuarioRepository.deleteById(id.intValue());
    }
}
