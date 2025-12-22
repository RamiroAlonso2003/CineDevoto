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

    public void reservarAsiento(int numeroAsiento) {
        validarAsientoNoReservado(numeroAsiento);
        AsientoReservado asiento = AsientoReservado.builder()
                .numero(numeroAsiento)
                .showtime(this)
                .build();
        asientosReservados.add(asiento);
    }

    public void cancelarReserva(String numeroAsiento) {
        asientosReservados.removeIf(a -> a.getNumero().equals(numeroAsiento));
    }

    public boolean estaReservado(String numeroAsiento) {
        return asientosReservados.stream()
                .anyMatch(a -> a.getNumero().equals(numeroAsiento));
    }

    private void validarAsientoNoReservado(String numeroAsiento) {
        if (estaReservado(numeroAsiento)) {
            throw new IllegalStateException(
                    "El asiento " + numeroAsiento + " ya está reservado"
            );
        }
    }
}
