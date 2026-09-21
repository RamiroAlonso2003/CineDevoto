import React from 'react';
import Badge from '../../Atoms/badge/Badge';
import BookingWidget from '../BookingWidget/BookingWidget';
import './MovieDetails.css';

const MovieDetails = ({ movie }) => {
  return (
    <div className="movie-details">
      {/* Header con título, género y duración */}
      <div className="movie-header">
        <h1 className="movie-title">{movie.titulo}</h1>
        <div className="movie-badges">
          {movie.genero && <Badge variant="genre">{movie.genero.nombre}</Badge>}
          <span className="movie-duration-badge">{movie.duracion} min</span>
        </div>
      </div>

      {/* Contenido principal */}
      <div className="movie-content">
        {/* Columna izquierda: Poster */}
        <div className="movie-left">
          <div className="movie-poster">
            <img
              src={movie.posterUrl || 'https://via.placeholder.com/300x450?text=Sin+imagen'}
              alt={movie.titulo}
            />
          </div>
          <a href="/" className="back-link">
            ← Volver a la cartelera
          </a>
        </div>

        {/* Columna derecha: Info y Widget */}
        <div className="movie-right">
          {/* Sinopsis */}
          <div className="movie-info-section">
            <h2>Sinopsis</h2>
            <p className="movie-synopsis">
              {movie.descripcion || 'Todavía no hay sinopsis cargada para esta película.'}
            </p>
          </div>

          {/* Widget de reserva */}
          <BookingWidget peliculaId={movie.peliculaId} />
        </div>
      </div>
    </div>
  );
};

export default MovieDetails;
