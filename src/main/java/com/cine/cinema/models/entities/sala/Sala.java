package com.cine.cinema.models.entities.sala;

import lombok.*;
import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "sala")
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Builder
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Sala {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Long salaId;

    @Column(nullable = false)
    private Integer filas;

    @Column(nullable = false)
    private Integer asientosPorFila;
}
