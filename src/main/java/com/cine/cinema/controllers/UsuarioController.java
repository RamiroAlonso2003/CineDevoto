package com.cine.cinema.controllers;

import com.cine.cinema.mapper.UsuarioMapper;
import com.cine.cinema.models.entities.usuario.UsuarioDto;
import com.cine.cinema.services.IUsuarioService;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

    private final IUsuarioService usuarioService;

    public UsuarioController(IUsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    @GetMapping
    @PreAuthorize("hasRole('ADMIN')")
    public List<UsuarioDto> listar() {
        return usuarioService.findAll().stream()
                .map(UsuarioMapper::toDto)
                .toList();
    }

    @GetMapping("/{id}")
    public UsuarioDto obtenerPorId(@PathVariable Long id) {
        return UsuarioMapper.toDto(usuarioService.findById(id));
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void eliminar(@PathVariable Long id) {
        usuarioService.deleteById(id);
    }
}
