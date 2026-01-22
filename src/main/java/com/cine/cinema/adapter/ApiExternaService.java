package com.cine.cinema.adapter;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.beans.factory.annotation.Value;
import java.util.Arrays;
import java.util.List;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import com.cine.cinema.models.entities.pelicula.PeliculaDto;

@Service
public class ApiExternaService {

    @Value("${api.externa.url}")
    private String apiUrl;

    private final RestTemplate restTemplate = new RestTemplate();

    // Obtiene todas las películas
    public List<PeliculaDto> obtenerPeliculasExternas() {
        PeliculaDto[] peliculas = restTemplate.getForObject(apiUrl, PeliculaDto[].class);
        return Arrays.asList(peliculas);
    }

    // Obtiene películas desde una fecha específica (si la API lo permite por parámetro)
    public List<PeliculaDto> obtenerPeliculasDesdeFecha(LocalDate fecha) {
        // Suponiendo que la API acepta un parámetro ?fecha=YYYY-MM-DD
        String urlConFecha = apiUrl + "?fecha=" + fecha.format(DateTimeFormatter.ISO_DATE);
        PeliculaDto[] peliculas = restTemplate.getForObject(urlConFecha, PeliculaDto[].class);
        return Arrays.asList(peliculas);
    }
}
