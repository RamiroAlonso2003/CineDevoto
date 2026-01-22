import React from 'react';
import Navbar from '../../organisms/navbar/Navbar';
import Hero from '../../organisms/hero/Hero';
import BookingWidget from '../../organisms/BookingWidget/BookingWidget';
import Boton from '../../Atoms/boton/boton';
import CardsGrid from '../CardsGrid/CardsGrid';
import Card from '../../organisms/card/card';
import './HomePage.css';

const HomePage = () => {
  return (
    <div className="home-page">
      <Navbar />
      <Hero />
      <BookingWidget />

      <CardsGrid>
        <Card id="1" title="Película 1" duration="16" genre="Accion" />
        <Card id="2" title="Película 2" description="Acción" />
        <Card id="3" title="Película 3" description="Sci-Fi" />
      </CardsGrid>
    </div>
  );
};

export default HomePage;
