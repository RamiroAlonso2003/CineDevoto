package com.cine.cinema.security;

import lombok.Data;

@Data
public class RegisterRequest {
    private String email;
    private String nombre;
    private String password;
}
