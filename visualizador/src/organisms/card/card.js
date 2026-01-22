import React from 'react';
import { useNavigate } from 'react-router-dom';
import './card.css';

const Card = ({ 
  id,
  title = 'Película sin título', 
  duration = '120 min', 
  imageUrl = 'https://via.placeholder.com/260x380?text=Sin+Imagen' 
}) => {
  const navigate = useNavigate();

  const handleClick = () => {
    if (id) {
      navigate(`/movie/${id}`);
    }
  };

  return (
    <div className="movie-card" onClick={handleClick} style={{ cursor: id ? 'pointer' : 'default' }}>
      <div className="movie-card-image">
        <img src={imageUrl} alt={title} />
      </div>
      <div className="movie-card-info">
        <div className="movie-duration">{duration}</div>
        <div className="movie-title">{title}</div>
      </div>
    </div>
  );
};

export default Card;
