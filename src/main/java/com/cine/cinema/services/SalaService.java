package com.cine.cinema.services;

import com.cine.cinema.models.entities.sala.Sala;
import com.cine.cinema.models.entities.sala.SalaDto;
import com.cine.cinema.models.repository.SalaRepository;
import com.cine.cinema.mapper.SalaMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class SalaService implements IsalaService {
    @Autowired
    private SalaRepository salaRepository;

    @Override
    public List<Sala> findAll() {
        return salaRepository.findAll();
    }

    @Override
    public Optional<Sala> findById(Integer id) {
        return salaRepository.findById(Math.toIntExact(Long.valueOf(id)));
    }

    @Override
    public Integer crearSala(SalaDto salaDto) {
        Sala sala = SalaMapper.fromDTO(salaDto);
        Sala guardada = salaRepository.save(sala);
        return guardada.getSalaId().intValue();
    }

    @Override
    public Sala actualizarSala(Integer id, SalaDto salaDto) {
        Sala sala = salaRepository.findById(Math.toIntExact(Long.valueOf(id)))
            .orElseThrow(() -> new RuntimeException("Sala inexistente"));
        sala.setFilas(salaDto.getFilas());
        sala.setAsientosPorFila(salaDto.getAsientosPorFila());
        return salaRepository.save(sala);
    }

    @Override
    public void deleteById(Integer id) {
        salaRepository.deleteById(Math.toIntExact(Long.valueOf(id)));
    }
}
