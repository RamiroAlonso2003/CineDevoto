import Boton from '../../Atoms/boton/boton';
import './Hero.css';

function Hero() {
  const irACartelera = () => {
    document.getElementById('cartelera')?.scrollIntoView({ behavior: 'smooth' });
  };

  return (
    <section className="hero">
      <div className="hero__inner">
        <h1 className="hero__title">Bienvenido a Cinema Devoto</h1>
        <p className="hero__copy">
          La mejor experiencia cinematográfica en el corazón de la ciudad. Seis salas,
          proyección láser, sonido Atmos.
        </p>
        <div className="hero__actions">
          <Boton variant="primary" onClick={irACartelera}>Ver cartelera</Boton>
          <Boton variant="ghost">Próximos estrenos</Boton>
        </div>
      </div>
    </section>
  );
}

export default Hero;
