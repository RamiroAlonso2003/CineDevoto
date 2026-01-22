import './Hero.css';

function Hero() {
  return (
    <section className="hero">
      <div className="hero-content">
        <h1 className="hero-title">
          Bienvenido a <span className="hero-highlight">Cinema Devoto</span>
        </h1>
        <p className="hero-subtitle">
          La mejor experiencia cinematográfica en el corazón de la ciudad
        </p>
        <div className="hero-buttons">
          <button className="hero-btn hero-btn-primary">Ver Cartelera</button>
          <button className="hero-btn hero-btn-secondary">Próximos Estrenos</button>
        </div>
      </div>
      <div className="hero-overlay"></div>
    </section>
  );
}

export default Hero;
