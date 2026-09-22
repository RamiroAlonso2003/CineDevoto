package com.cine.cinema.scheduler;

import com.cine.cinema.adapter.ApiExternaService;
import com.cine.cinema.models.entities.pelicula.PeliculaDto;
import com.cine.cinema.services.IPeliculaService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.boot.CommandLineRunner;
import org.springframework.web.client.RestClientException;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class ImportadorPeliculasScheduler implements CommandLineRunner {

    private static final Logger log = LoggerFactory.getLogger(ImportadorPeliculasScheduler.class);

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
        if (apiExternaService.noConfigurada()) {
            log.warn("api.externa.url no está configurada: se omite la importación de películas");
            return;
        }
        List<PeliculaDto> peliculas;
        try {
            if (primeraVez) {
                peliculas = apiExternaService.obtenerPeliculasExternas();
            } else {
                LocalDate fechaLimite = LocalDate.now();
                peliculas = apiExternaService.obtenerPeliculasDesdeFecha(fechaLimite);
            }
        } catch (RestClientException e) {
            // Si la API externa no responde, la app sigue funcionando sin importar películas
            log.warn("No se pudieron importar películas desde la API externa: {}", e.getMessage());
            return;
        }
        for (PeliculaDto dto : peliculas) {
            peliculaService.crearPelicula(dto);
        }
    }
}
