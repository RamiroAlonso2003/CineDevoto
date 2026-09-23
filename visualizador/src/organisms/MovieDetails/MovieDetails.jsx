import React from 'react';
import { Link } from 'react-router-dom';
import Badge from '../../Atoms/badge/Badge';
import BookingWidget from '../BookingWidget/BookingWidget';
import './MovieDetails.css';

const MovieDetails = ({ movie }) => {
  return (
    <div className="detail">
      <div className="detail__poster">
        <img
          src={movie.posterUrl || undefined}
          alt={movie.titulo}
          className="detail__poster-img"
        />
      </div>

      <div className="detail__info">
        <Link to="/" className="detail__back">← Volver a la cartelera</Link>

        <h1 className="detail__title">{movie.titulo}</h1>

        <div className="detail__tags">
          {movie.genero && <Badge>{movie.genero.nombre}</Badge>}
          <span className="detail__duration">{movie.duracion} min</span>
        </div>

        <p className="detail__synopsis">
          {movie.descripcion || 'Todavía no hay sinopsis cargada para esta película.'}
        </p>

        <BookingWidget peliculaId={movie.peliculaId} />
      </div>
    </div>
  );
};

export default MovieDetails;
