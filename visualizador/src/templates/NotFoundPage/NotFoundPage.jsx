import { Link } from 'react-router-dom';
import Navbar from '../../organisms/navbar/Navbar';
import Boton from '../../Atoms/boton/boton';
import './NotFoundPage.css';

function NotFoundPage() {
  return (
    <div>
      <Navbar />
      <div className="page-wrap notfound">
        <p className="eyebrow">Error 404</p>
        <h1 className="notfound__title">Esta página no existe</h1>
        <p className="notfound__copy">
          Puede que el link esté roto o que la función ya no esté disponible.
        </p>
        <Link to="/">
          <Boton variant="primary">Volver al inicio</Boton>
        </Link>
      </div>
    </div>
  );
}

export default NotFoundPage;
