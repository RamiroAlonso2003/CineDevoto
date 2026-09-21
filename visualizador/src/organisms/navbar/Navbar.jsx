import { Link } from "react-router-dom";
import "./Navbar.css";
import LogoIcon from "../../Atoms/logoIcon/logoIcon";
import { useAuth } from "../../context/AuthContext";

function Navbar() {
  const { isAuthenticated, email, logout } = useAuth();

  return (
    <header className="navbar">
      <Link to="/" className="navbar-logo-link">
        <LogoIcon></LogoIcon>
      </Link>

      <div className="navbar-auth">
        {isAuthenticated ? (
          <>
            <span className="navbar-email">{email}</span>
            <button type="button" className="navbar-link-btn" onClick={logout}>
              Cerrar sesión
            </button>
          </>
        ) : (
          <>
            <Link to="/login" className="nav-link">Iniciar sesión</Link>
            <Link to="/register" className="nav-link">Registrarme</Link>
          </>
        )}
      </div>
    </header>
  );
}

export default Navbar;
