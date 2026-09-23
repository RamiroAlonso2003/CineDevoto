package com.cine.cinema.exception;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    // Choca contra un unique constraint (ej: dos usuarios reservando el mismo
    // asiento al mismo tiempo) -> conflicto, no un error del servidor.
    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<String> handleConflictoDeDatos(DataIntegrityViolationException ex) {
        return ResponseEntity.status(HttpStatus.CONFLICT)
                .body("La operación entra en conflicto con datos existentes (ej: un asiento ya reservado por otro usuario).");
    }

    // Reglas de negocio violadas (asiento ya reservado, horarios que se
    // solapan, email duplicado, etc.) -> también conflicto, no 500.
    @ExceptionHandler(IllegalStateException.class)
    public ResponseEntity<String> handleEstadoInvalido(IllegalStateException ex) {
        return ResponseEntity.status(HttpStatus.CONFLICT).body(ex.getMessage());
    }
}
