package com.cine.cinema.scheduler;

import com.cine.cinema.adapter.ApiExternaService;
import com.cine.cinema.models.entities.pelicula.PeliculaDto;
import com.cine.cinema.services.IPeliculaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.boot.CommandLineRunner;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class ImportadorPeliculasScheduler implements CommandLineRunner {

    @Autowired
    private ApiExternaService apiExternaService;

    @Autowired
    private IPeliculaService peliculaService;

    // Ejecuta cada día a las 03:00 AM
    @Scheduled(cron = "0 0 3 * * *")
    public void importarPeliculasDesdeFecha() {
        importarPeliculas(false);
    }

    // Ejecuta al iniciar la aplicación
    @Override
    public void run(String... args) {
        importarPeliculas(true);
    }

    private void importarPeliculas(boolean primeraVez) {
        List<PeliculaDto> peliculas;
        if (primeraVez) {
            peliculas = apiExternaService.obtenerPeliculasExternas();
        } else {
            LocalDate fechaLimite = LocalDate.now();
            peliculas = apiExternaService.obtenerPeliculasDesdeFecha(fechaLimite);
        }
        for (PeliculaDto dto : peliculas) {
            peliculaService.crearPelicula(dto);
        }
    }
}
