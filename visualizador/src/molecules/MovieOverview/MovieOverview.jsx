import React from 'react';
import Heading from '../../Atoms/heading/Heading';
import Text from '../../Atoms/text/Text';
import './MovieOverview.css';

const MovieOverview = ({ tagline, overview, creator }) => {
  return (
    <div className="movie-overview">
      {tagline && (
        <Text variant="large" className="movie-tagline">{tagline}</Text>
      )}
      
      <Heading level={3}>Vista general</Heading>
      <Text variant="body" className="movie-description">{overview}</Text>
      
      {creator && (
        <div className="movie-creator">
          <Heading level={4}>{creator.name}</Heading>
          <Text variant="small">{creator.role}</Text>
        </div>
      )}
    </div>
  );
};

export default MovieOverview;
