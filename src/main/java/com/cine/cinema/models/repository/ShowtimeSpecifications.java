package com.cine.cinema.models.repository;

import com.cine.cinema.models.entities.showtime.Showtime;
import org.springframework.data.jpa.domain.Specification;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class ShowtimeSpecifications {

    public static Specification<Showtime> conPelicula(Integer peliculaId) {
        return (root, query, cb) ->
                peliculaId == null
                        ? cb.conjunction()
                        : cb.equal(
                        root.get("pelicula").get("peliculaId"),
                        peliculaId
                );
    }

    public static Specification<Showtime> conDiaInicio(LocalDate fecha) {
        return (root, query, cb) -> {
            if (fecha == null) {
                return cb.conjunction();
            }

            LocalDateTime inicioDia = fecha.atStartOfDay();
            LocalDateTime finDia = fecha.atTime(23, 59, 59);

            return cb.between(root.get("inicio"), inicioDia, finDia);
        };
    }
}