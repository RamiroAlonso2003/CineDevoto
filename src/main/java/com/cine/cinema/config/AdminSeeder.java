package com.cine.cinema.config;

import com.cine.cinema.models.entities.usuario.Rol;
import com.cine.cinema.models.entities.usuario.Usuario;
import com.cine.cinema.models.repository.UsuarioRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class AdminSeeder implements CommandLineRunner {

    private static final Logger logger = LoggerFactory.getLogger(AdminSeeder.class);

    private final UsuarioRepository usuarioRepository;
    private final PasswordEncoder passwordEncoder;

    @Value("${admin.seed.email:admin}")
    private String email;

    @Value("${admin.seed.password:admin}")
    private String password;

    public AdminSeeder(UsuarioRepository usuarioRepository, PasswordEncoder passwordEncoder) {
        this.usuarioRepository = usuarioRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void run(String... args) {
        if (usuarioRepository.existsByEmailIgnoreCase(email)) {
            return;
        }

        Usuario admin = Usuario.builder()
                .email(email)
                .nombre("Administrador")
                .password(passwordEncoder.encode(password))
                .rol(Rol.ADMIN)
                .build();

        usuarioRepository.save(admin);
        logger.info("Usuario ADMIN creado ({}). Cambiá la contraseña por defecto en producción.", email);
    }
}
