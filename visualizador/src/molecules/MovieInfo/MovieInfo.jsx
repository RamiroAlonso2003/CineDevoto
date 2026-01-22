import React from 'react';
import Badge from '../../Atoms/badge/Badge';
import './MovieInfo.css';

const MovieInfo = ({ title, year, genres = [], duration, rating }) => {
  return (
    <div className="movie-info">
      <div className="movie-info-header">
        <h1 className="movie-info-title">
          {title} <span className="movie-info-year">({year})</span>
        </h1>
      </div>
      
      <div className="movie-info-meta">
        {rating && (
          <Badge variant="default">TV-{rating}</Badge>
        )}
        {genres.map((genre, index) => (
          <Badge key={index} variant="genre">{genre}</Badge>
        ))}
        {duration && (
          <span className="movie-info-duration">{duration}</span>
        )}
      </div>
    </div>
  );
};

export default MovieInfo;
