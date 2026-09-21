import React, { useEffect, useState } from 'react';
import { useParams } from 'react-router-dom';
import Navbar from '../../organisms/navbar/Navbar';
import MovieDetails from '../../organisms/MovieDetails/MovieDetails';
import { getPeliculaPorId } from '../../api/peliculas';
import './MovieDetailPage.css';

const MovieDetailPage = () => {
  const { id } = useParams();
  const [movie, setMovie] = useState(null);
  const [cargando, setCargando] = useState(true);
  const [error, setError] = useState(false);

  useEffect(() => {
    setCargando(true);
    setError(false);
    getPeliculaPorId(id)
      .then(setMovie)
      .catch(() => setError(true))
      .finally(() => setCargando(false));
  }, [id]);

  if (cargando) {
    return (
      <div className="movie-detail-page">
        <Navbar />
        <div className="movie-not-found">
          <h2>Cargando…</h2>
        </div>
      </div>
    );
  }

  if (error || !movie) {
    return (
      <div className="movie-detail-page">
        <Navbar />
        <div className="movie-not-found">
          <h2>Película no encontrada</h2>
        </div>
      </div>
    );
  }

  return (
    <div className="movie-detail-page">
      <Navbar />
      <MovieDetails movie={movie} />
    </div>
  );
};

export default MovieDetailPage;
