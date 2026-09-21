package com.cine.cinema.controllers;

import com.cine.cinema.models.entities.usuario.Rol;
import com.cine.cinema.models.entities.usuario.Usuario;
import com.cine.cinema.security.JwtService;
import com.cine.cinema.security.LoginRequest;
import com.cine.cinema.security.RegisterRequest;
import com.cine.cinema.security.TokenResponse;
import com.cine.cinema.services.IUsuarioService;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final IUsuarioService usuarioService;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    public AuthController(IUsuarioService usuarioService,
                           JwtService jwtService,
                           AuthenticationManager authenticationManager) {
        this.usuarioService = usuarioService;
        this.jwtService = jwtService;
        this.authenticationManager = authenticationManager;
    }

    @PostMapping("/register")
    @ResponseStatus(HttpStatus.CREATED)
    public TokenResponse register(@RequestBody RegisterRequest req) {
        Usuario usuario = Usuario.builder()
                .email(req.getEmail())
                .nombre(req.getNombre())
                .password(req.getPassword())
                .rol(Rol.CLIENTE)
                .build();

        Usuario guardado = usuarioService.registrar(usuario);
        return new TokenResponse(jwtService.generarToken(guardado));
    }

    @PostMapping("/login")
    public TokenResponse login(@RequestBody LoginRequest req) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(req.getEmail(), req.getPassword()));

        Usuario usuario = usuarioService.findByEmail(req.getEmail());
        return new TokenResponse(jwtService.generarToken(usuario));
    }
}
