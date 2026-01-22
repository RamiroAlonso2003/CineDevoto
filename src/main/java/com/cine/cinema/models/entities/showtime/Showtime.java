package com.cine.cinema.models.entities.showtime;

import com.cine.cinema.models.entities.pelicula.Pelicula;
import com.cine.cinema.models.entities.sala.Sala;
import lombok.*;
import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "showtime")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Builder
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Showtime {
    

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    @Column(name = "showtime_id")
    private Integer showtimeId;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "pelicula_id", nullable = false)
    private Pelicula pelicula;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "sala_id", nullable = false)
    private Sala sala;

    @Column(name = "inicio", nullable = false)
    private LocalDateTime inicio;

    @Column(name = "fin", nullable = false)
    private LocalDateTime fin;

    @OneToMany(
            mappedBy = "showtime",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    @Builder.Default
    private Set<AsientoReservado> asientosReservados = new HashSet<AsientoReservado>();

    /* ==========================
       MÉTODOS DE DOMINIO
       ========================== */

    public void reservarAsiento(String fila, Integer numero) {
        validarAsientoNoReservado(fila, numero);
        AsientoReservado asiento = new AsientoReservado(fila, numero, this);
        asientosReservados.add(asiento);
    }

    private void validarAsientoNoReservado(String fila, Integer numero) {
        boolean reservado = asientosReservados.stream()
            .anyMatch(a -> a.getFila().equals(fila) && a.getNumero().equals(numero));
        if (reservado) {
            throw new IllegalStateException("El asiento " + fila + "-" + numero + " ya está reservado");
        }
    }


    public void cancelarReserva(String numeroAsiento) {
        asientosReservados.removeIf(a -> a.getNumero().equals(numeroAsiento));
    }

    public boolean estaReservado(String numeroAsiento) {
        return asientosReservados.stream()
                .anyMatch(a -> a.getNumero().equals(numeroAsiento));
    }

}
