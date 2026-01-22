import React from 'react';
import Badge from '../../Atoms/badge/Badge';
import BookingWidget from '../BookingWidget/BookingWidget';
import './MovieDetails.css';

const MovieDetails = ({ movie }) => {
  return (
    <div className="movie-details">
      {/* Header con título, badges y rating */}
      <div className="movie-header">
        <h1 className="movie-title">{movie.title}</h1>
        <div className="movie-badges">
          {movie.genres.map((genre, index) => (
            <Badge key={index} variant="genre">{genre}</Badge>
          ))}
          <div className="movie-rating">
            <span className="rating-star">★</span>
            <span className="rating-value">{movie.score / 10}</span>
          </div>
        </div>
      </div>

      {/* Contenido principal */}
      <div className="movie-content">
        {/* Columna izquierda: Poster */}
        <div className="movie-left">
          <div className="movie-poster">
            <img src={movie.posterImage} alt={movie.title} />
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
            <p className="movie-synopsis">{movie.overview}</p>
            
            <div className="movie-credits">
              <p><strong>Director:</strong> {movie.director || movie.creator?.name}</p>
              <p><strong>Elenco:</strong> {movie.cast || 'Información no disponible'}</p>
            </div>
          </div>

          {/* Widget de reserva */}
          <BookingWidget movieId={movie.id} />
        </div>
      </div>
    </div>
  );
};

export default MovieDetails;
