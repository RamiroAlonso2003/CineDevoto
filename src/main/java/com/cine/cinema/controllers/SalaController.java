package com.cine.cinema.controllers;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/salas")
@RequiredArgsConstructor
public class SalaController {
    
    private static final Logger logger =
    LoggerFactory.getLogger(SalaController.class);

    private final SalaService salaService;

    public SalaController(SalaService salaService) {
        this.salaService = salaService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Integer crearSala(@RequestBody SalaDto salaDto) {
        logger.info("Solicitud para crear una nueva sala");
        return salaService.save(salaDto).getSalaId();
    }

    @GetMapping("")
    public List<SalaDto> obtenerSalas() {
        logger.info("Solicitud para obtener todas las salas");
        return salaService.findAll()
                .stream()
                .map(SalaMapper::toDTO)
                .orElse(null);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void eliminarSala(@PathVariable Integer id) {
        logger.info("Solicitud para eliminar la sala con id {}", id);
        salaService.deleteById(id);
    }
}
