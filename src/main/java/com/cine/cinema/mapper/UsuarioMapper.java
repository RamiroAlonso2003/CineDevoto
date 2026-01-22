package com.cine.cinema.mapper;

import com.cine.cinema.models.entities.usuario.Usuario;
import com.cine.cinema.models.entities.usuario.UsuarioDto;

public class UsuarioMapper {
    public static UsuarioDto toDto(Usuario usuario) {
        if (usuario == null) return null;
        UsuarioDto dto = new UsuarioDto();
        dto.setUsuarioId(usuario.getUsuarioId());
        dto.setNombre(usuario.getNombre());
        dto.setEmail(usuario.getEmail());
        dto.setKeycloakId(usuario.getKeycloakId());
        return dto;
    }

    public static Usuario fromDto(UsuarioDto dto) {
        if (dto == null) return null;
        return Usuario.builder()
                .usuarioId(dto.getUsuarioId())
                .nombre(dto.getNombre())
                .email(dto.getEmail())
                .keycloakId(dto.getKeycloakId())
                .build();
    }
}
