import React from 'react';
import { useParams } from 'react-router-dom';
import Navbar from '../../organisms/navbar/Navbar';
import MovieDetails from '../../organisms/MovieDetails/MovieDetails';
import './MovieDetailPage.css';

// Datos de ejemplo - puedes reemplazar esto con una API
const moviesData = {
  1: {
    id: 1,
    title: 'LA LA LAND',
    year: '2016',
    genres: ['Musical', 'Drama'],
    score: 80,
    overview: 'Mia, una joven aspirante a actriz que trabaja como camarera, y Sebastian, un músico de jazz que se gana la vida tocando en tugurios, se enamoran, pero su gran ambición por llegar a la cima amenaza con separarlos.',
    director: 'Damien Chazelle',
    cast: 'Ryan Gosling, Emma Stone, J.K. Simmons',
    posterImage: 'https://image.tmdb.org/t/p/w500/uDO8zWDhfWwoFdKS4fzkUJt0Rf0.jpg'
  },
  2: {
    id: 2,
    title: 'Ley y orden: Unidad de Víctimas Especiales',
    year: '1999',
    genres: ['Crimen', 'Drama', 'Misterio'],
    score: 79,
    overview: "'Ley y Orden: Unidad de Víctimas Especiales' es una serie de televisión estadounidense grabada en Nueva York donde es también principalmente producida. Con el estilo de la original 'Ley y Orden' los episodios son usualmente \"sacados de los titulares\" o basados libremente en verdaderos asesinatos que han recibido la atención de los medios.",
    director: 'Dick Wolf',
    cast: 'Mariska Hargitay, Ice-T, Christopher Meloni',
    posterImage: 'https://image.tmdb.org/t/p/w500/qXBjyRSeRc8yJTRlfioResGYKK.jpg'
  },
  3: {
    id: 3,
    title: 'Película 3',
    year: '2023',
    genres: ['Sci-Fi', 'Thriller'],
    score: 92,
    overview: 'Una película de ciencia ficción que explora los límites de la tecnología y la humanidad en un futuro distópico.',
    director: 'Director Sci-Fi',
    cast: 'Actor 1, Actor 2, Actor 3',
    posterImage: 'https://via.placeholder.com/300x450?text=Película+3'
  }
};

const MovieDetailPage = () => {
  const { id } = useParams();
  const movie = moviesData[id];

  if (!movie) {
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
