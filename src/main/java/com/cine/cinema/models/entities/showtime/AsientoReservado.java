package com.cine.cinema.models.entities.showtime;

import com.cine.cinema.models.entities.reserva.Reserva;
import com.cine.cinema.models.entities.showtime.Showtime;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(
        name = "asiento_reservado",
        uniqueConstraints = {
                @UniqueConstraint(columnNames = {
                        "showtime_id", "fila", "numero"
                })
        }
)
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class AsientoReservado {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Long id;

    @Column(nullable = false)
    private String fila;

    @Column(nullable = false)
    private Integer numero;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "showtime_id", nullable = false)
    private Showtime showtime;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "reserva_id")
    private Reserva reserva;

    protected AsientoReservado(String fila, Integer numero, Showtime showtime) {
        this.fila = fila;
        this.numero = numero;
        this.showtime = showtime;
    }

    /* ======================
       MÉTODOS DE DOMINIO
       ====================== */

    public void asignarReserva(Reserva reserva) {
        this.reserva = reserva;
    }

    public void liberarReserva() {
        this.reserva = null;
    }
}
