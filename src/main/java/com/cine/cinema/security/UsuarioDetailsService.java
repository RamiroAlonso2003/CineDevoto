package com.cine.cinema.security;

import com.cine.cinema.models.entities.usuario.Usuario;
import com.cine.cinema.services.IUsuarioService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UsuarioDetailsService implements UserDetailsService {

    private final IUsuarioService usuarioService;

    public UsuarioDetailsService(IUsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    @Override
    public UserDetails loadUserByUsername(String email) {
        Usuario usuario;
        try {
            usuario = usuarioService.findByEmail(email);
        } catch (RuntimeException e) {
            throw new UsernameNotFoundException("Usuario inexistente: " + email);
        }

        return org.springframework.security.core.userdetails.User
                .withUsername(usuario.getEmail())
                .password(usuario.getPassword())
                .roles(usuario.getRol().name())
                .build();
    }
}
