package com.cine.cinema.services;

import com.cine.cinema.models.entities.sala.Sala;
import com.cine.cinema.models.entities.sala.SalaDto;
import java.util.List;
import java.util.Optional;

public interface IsalaService {
    List<Sala> findAll();
    Optional<Sala> findById(Integer id);
    Integer crearSala(SalaDto salaDto);
    Sala actualizarSala(Integer id, SalaDto salaDto);
    void deleteById(Integer id);
}
