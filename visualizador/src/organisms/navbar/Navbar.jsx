import { Link } from "react-router-dom";
import "./Navbar.css";
import { useAuth } from "../../context/AuthContext";

function Navbar() {
  const { isAuthenticated, email, logout } = useAuth();

  return (
    <header className="navbar">
      <div className="navbar__inner">
        <Link to="/" className="navbar__brand">
          <span className="navbar__mark" />
          <span className="navbar__name">
            <strong>DEVOTO SHOPPING</strong>
            <span>CINEMA</span>
          </span>
        </Link>

        <div className="navbar__spacer" />

        <div className="navbar__actions">
          {isAuthenticated ? (
            <>
              <span className="navbar__user">{email}</span>
              <button type="button" className="boton boton--on-brand" onClick={logout}>
                Cerrar sesión
              </button>
            </>
          ) : (
            <>
              <Link to="/login" className="navbar__user">Iniciar sesión</Link>
              <Link to="/register" className="boton boton--on-brand">Registrarme</Link>
            </>
          )}
        </div>
      </div>
    </header>
  );
}

export default Navbar;
