package com.cine.cinema.controllers;

import com.cine.cinema.mapper.PeliculaMapper;
import com.cine.cinema.models.entities.pelicula.Pelicula;
import com.cine.cinema.models.entities.pelicula.PeliculaDto;
import com.cine.cinema.services.IPeliculaService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/peliculas")
public class PeliculasController {

    private static final Logger logger = LoggerFactory.getLogger(PeliculasController.class);

    private final IPeliculaService peliculaService;

    public PeliculasController(IPeliculaService peliculaService) {
        this.peliculaService = peliculaService;
    }

    /**
     * Crea una nueva película. devuelve el id
     */
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Long crearPelicula(@RequestBody PeliculaDto peliculaDTO) {
        logger.info("Solicitud para crear una nueva película");
        return peliculaService.crearPelicula(peliculaDTO);
    }

    /**
     * Obtiene una película por ID.
     */
    @GetMapping("/{id}")
    public PeliculaDto obtenerPeliculaPorId(@PathVariable Integer id) {
        logger.info("Solicitud para obtener la película con id {}", id);
        return PeliculaMapper.toDTO(peliculaService.obtenerPeliculaPorId(id));
    }

    /**
     * Elimina una película por ID.
     */
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void eliminarPelicula(@PathVariable Integer id) {
        logger.info("Solicitud para eliminar la película con id {}", id);
        peliculaService.eliminarPeliculaPorId(id);
    }

    /**
     * Modifica una película existente.
     */
    @PutMapping("/{id}")
    public Pelicula modificarPelicula(
            @PathVariable Integer id,
            @RequestBody PeliculaDto peliculaDTO) {

        logger.info("Solicitud para modificar la película con id {}", id);
        return peliculaService.actualizarPelicula(id, peliculaDTO);
    }
}
