import { useNavigate } from 'react-router-dom';
import Boton from '../../Atoms/boton/boton';
import './Hero.css';

function Hero() {
  const navigate = useNavigate();

  const irACartelera = () => {
    document.getElementById('cartelera')?.scrollIntoView({ behavior: 'smooth' });
  };

  return (
    <section className="hero">
      <div className="hero__inner">
        <p className="hero__kicker">Cine en el corazón de la ciudad</p>
        <h1 className="hero__title">Vení a vivir el cine como se debe</h1>
        <p className="hero__copy">
          Pantallas grandes, butacas cómodas y la cartelera actualizada todas las semanas.
        </p>
        <div className="hero__actions">
          <Boton variant="primary" onClick={irACartelera}>Ver cartelera</Boton>
          <Boton variant="ghost" onClick={() => navigate('/register')}>Registrarme</Boton>
        </div>
      </div>
    </section>
  );
}

export default Hero;
