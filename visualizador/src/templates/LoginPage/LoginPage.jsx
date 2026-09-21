import { useState } from 'react';
import { Link, useLocation, useNavigate } from 'react-router-dom';
import Navbar from '../../organisms/navbar/Navbar';
import FormField from '../../molecules/FormField/FormField';
import Input from '../../Atoms/input/Input';
import Boton from '../../Atoms/boton/boton';
import { useAuth } from '../../context/AuthContext';
import './LoginPage.css';

function LoginPage() {
  const { login } = useAuth();
  const navigate = useNavigate();
  const location = useLocation();
  const destino = location.state?.from?.pathname || '/';

  const [email, setEmail] = useState('');
  const [password, setPassword] = useState('');
  const [error, setError] = useState(null);
  const [enviando, setEnviando] = useState(false);

  const handleSubmit = async (evento) => {
    evento.preventDefault();
    setError(null);
    setEnviando(true);
    try {
      await login(email, password);
      navigate(destino, { replace: true });
    } catch (err) {
      setError('No pudimos iniciar sesión. Revisá tu email y contraseña.');
    } finally {
      setEnviando(false);
    }
  };

  return (
    <div className="auth-page">
      <Navbar />
      <div className="auth-container">
        <form className="auth-card" onSubmit={handleSubmit}>
          <h1 className="auth-title">Iniciar sesión</h1>

          <FormField label="Email" htmlFor="login-email" required>
            <Input
              type="email"
              name="email"
              placeholder="tu@email.com"
              value={email}
              onChange={(e) => setEmail(e.target.value)}
              required
            />
          </FormField>

          <FormField label="Contraseña" htmlFor="login-password" required>
            <Input
              type="password"
              name="password"
              placeholder="••••••••"
              value={password}
              onChange={(e) => setPassword(e.target.value)}
              required
            />
          </FormField>

          {error && <p className="auth-error">{error}</p>}

          <Boton type="submit" variant="primary" size="md" disabled={enviando}>
            {enviando ? 'Ingresando…' : 'Ingresar'}
          </Boton>

          <p className="auth-switch">
            ¿No tenés cuenta? <Link to="/register">Registrate</Link>
          </p>
        </form>
      </div>
    </div>
  );
}

export default LoginPage;
