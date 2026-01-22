package com.cine.cinema.controllers;

import com.cine.cinema.models.entities.sala.Sala;
import com.cine.cinema.models.entities.sala.SalaDto;
import com.cine.cinema.services.IsalaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/salas")
public class SalaController {
    @Autowired
    private IsalaService salaService;

    @GetMapping
    public List<Sala> findAll() {
        return salaService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Sala> findById(@PathVariable Integer id) {
        Optional<Sala> sala = salaService.findById(id);
        return sala.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Integer> crearSala(@RequestBody SalaDto salaDto) {
        Integer id = salaService.crearSala(salaDto);
        return ResponseEntity.ok(id);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Sala> actualizarSala(@PathVariable Integer id, @RequestBody SalaDto salaDto) {
        Sala sala = salaService.actualizarSala(id, salaDto);
        return ResponseEntity.ok(sala);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable Integer id) {
        salaService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
