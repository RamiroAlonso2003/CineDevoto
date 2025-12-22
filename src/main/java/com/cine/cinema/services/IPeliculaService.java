package com.cine.cinema.services;

import com.cine.cinema.models.entities.pelicula.*;

public interface IPeliculaService {

    /**
     * Crea una nueva película y devuelve su ID.
     */
    Long crearPelicula(PeliculaDto peliculaDTO);

    /**
     * Obtiene una película por su ID.
     */
    Pelicula obtenerPeliculaPorId(Integer id);

    /**
     * Elimina una película por su ID.
     */
    void eliminarPeliculaPorId(Integer id);

    /**
     * Actualiza una película existente.
     */
    Pelicula actualizarPelicula(Integer id, PeliculaDto peliculaDTO);
}
