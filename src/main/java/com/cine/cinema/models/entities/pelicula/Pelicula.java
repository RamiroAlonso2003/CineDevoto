package com.cine.cinema.models.entities.pelicula;

import lombok.*;
import jakarta.persistence.*;
import java.time.LocalDate;
import com.cine.cinema.models.entities.genero.Genero;

@Entity
@Table(name = "pelicula")
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Builder
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Pelicula {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Long peliculaId;

    @Column(nullable = false)
    private String titulo;

    private String descripcion;

    private String posterUrl;

    @ManyToOne(optional = false)
    @JoinColumn(name = "genero_id")
    private Genero genero;
}
