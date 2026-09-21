import React, { useEffect, useState } from 'react';
import Navbar from '../../organisms/navbar/Navbar';
import Hero from '../../organisms/hero/Hero';
import CardsGrid from '../CardsGrid/CardsGrid';
import Card from '../../organisms/card/card';
import { getPeliculas } from '../../api/peliculas';
import './HomePage.css';

const HomePage = () => {
  const [peliculas, setPeliculas] = useState([]);
  const [cargando, setCargando] = useState(true);
  const [error, setError] = useState(null);

  useEffect(() => {
    getPeliculas()
      .then(setPeliculas)
      .catch((err) => setError(err.message))
      .finally(() => setCargando(false));
  }, []);

  return (
    <div className="home-page">
      <Navbar />
      <Hero />

      <div id="cartelera" className="page-wrap home-page__cartelera">
        <p className="eyebrow">En cartelera</p>

        <CardsGrid>
          {cargando && <p className="cartelera-estado">Cargando cartelera…</p>}
          {error && (
            <p className="cartelera-estado cartelera-error">
              No se pudo cargar la cartelera: {error}
            </p>
          )}
          {!cargando && !error && peliculas.length === 0 && (
            <p className="cartelera-estado">Todavía no hay películas cargadas.</p>
          )}
          {peliculas.map((pelicula) => (
            <Card
              key={pelicula.peliculaId}
              id={pelicula.peliculaId}
              title={pelicula.titulo}
              duration={`${pelicula.duracion} min`}
              imageUrl={pelicula.posterUrl || undefined}
            />
          ))}
        </CardsGrid>
      </div>
    </div>
  );
};

export default HomePage;
