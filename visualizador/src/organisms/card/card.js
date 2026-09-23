import React from 'react';
import { useNavigate } from 'react-router-dom';
import './card.css';

const Card = ({
  id,
  title = 'Película sin título',
  duration = '120 min',
  imageUrl
}) => {
  const navigate = useNavigate();

  const handleClick = () => {
    if (id) {
      navigate(`/movie/${id}`);
    }
  };

  return (
    <div className="card" onClick={handleClick} style={{ cursor: id ? 'pointer' : 'default' }}>
      <div
        className="card__poster"
        style={imageUrl ? { backgroundImage: `url(${imageUrl})` } : undefined}
      />
      <div className="card__body">
        <div className="card__meta">{duration}</div>
        <div className="card__title">{title}</div>
      </div>
    </div>
  );
};

export default Card;
