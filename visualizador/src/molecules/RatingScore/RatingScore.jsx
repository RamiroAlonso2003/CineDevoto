import React from 'react';
import Badge from '../../Atoms/badge/Badge';
import './RatingScore.css';

const RatingScore = ({ score }) => {
  return (
    <div className="rating-score">
      <Badge variant="rating">
        {score}%
      </Badge>
      <div className="rating-text">
        <div className="rating-label">Puntuación</div>
        <div className="rating-sublabel">de usuarios</div>
      </div>
    </div>
  );
};

export default RatingScore;
