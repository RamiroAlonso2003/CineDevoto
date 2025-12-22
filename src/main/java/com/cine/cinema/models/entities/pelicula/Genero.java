package com.cine.cinema.models.entities.genero;

import lombok.*;
import jakarta.persistence.*;

@Entity
@Table(name = "genero")
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Builder
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Genero {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Long generoId;

    @Column(nullable = false)
    private String nombre;
}
