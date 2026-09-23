import React from 'react';
import './Badge.css';

const Badge = ({ children, variant }) => {
  return (
    <span className={`badge${variant ? ` badge--${variant}` : ''}`}>
      {children}
    </span>
  );
};

export default Badge;
