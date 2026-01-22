import React from 'react';
import './Text.css';

const Text = ({ children, variant = 'body', className = '' }) => {
  return (
    <p className={`text text-${variant} ${className}`}>
      {children}
    </p>
  );
};

export default Text;
