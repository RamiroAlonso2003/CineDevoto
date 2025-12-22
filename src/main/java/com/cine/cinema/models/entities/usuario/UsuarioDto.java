package com.cine.cinema.models.entities.usuario;

import lombok.Data;

@Data
public class UsuarioDto {
    private Long usuarioId;
    private String email;
    private String nombre;
    private java.util.Date keycloakId;
}