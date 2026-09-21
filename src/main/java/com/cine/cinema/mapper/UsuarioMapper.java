package com.cine.cinema.mapper;

import com.cine.cinema.models.entities.usuario.Rol;
import com.cine.cinema.models.entities.usuario.Usuario;
import com.cine.cinema.models.entities.usuario.UsuarioDto;

public class UsuarioMapper {
    public static UsuarioDto toDto(Usuario usuario) {
        if (usuario == null) return null;
        UsuarioDto dto = new UsuarioDto();
        dto.setUsuarioId(usuario.getUsuarioId());
        dto.setNombre(usuario.getNombre());
        dto.setEmail(usuario.getEmail());
        dto.setRol(usuario.getRol());
        dto.setKeycloakId(usuario.getKeycloakId());
        return dto;
    }

    // No incluye password: este mapper nunca debe usarse para crear/actualizar
    // credenciales, eso pasa exclusivamente por AuthController + UsuarioService.
    public static Usuario fromDto(UsuarioDto dto) {
        if (dto == null) return null;
        return Usuario.builder()
                .usuarioId(dto.getUsuarioId())
                .nombre(dto.getNombre())
                .email(dto.getEmail())
                .rol(dto.getRol() != null ? dto.getRol() : Rol.CLIENTE)
                .keycloakId(dto.getKeycloakId())
                .build();
    }
}
