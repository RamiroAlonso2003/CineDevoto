import { useState } from 'react';
import { Link, useNavigate } from 'react-router-dom';
import Navbar from '../../organisms/navbar/Navbar';
import FormField from '../../molecules/FormField/FormField';
import Input from '../../Atoms/input/Input';
import Boton from '../../Atoms/boton/boton';
import { useAuth } from '../../context/AuthContext';
import '../LoginPage/LoginPage.css';

function RegisterPage() {
  const { register } = useAuth();
  const navigate = useNavigate();

  const [nombre, setNombre] = useState('');
  const [email, setEmail] = useState('');
  const [password, setPassword] = useState('');
  const [error, setError] = useState(null);
  const [enviando, setEnviando] = useState(false);

  const handleSubmit = async (evento) => {
    evento.preventDefault();
    setError(null);
    setEnviando(true);
    try {
      await register(email, nombre, password);
      navigate('/', { replace: true });
    } catch (err) {
      setError('No pudimos crear la cuenta. Puede que el email ya esté registrado.');
    } finally {
      setEnviando(false);
    }
  };

  return (
    <div className="auth-page">
      <Navbar />
      <div className="auth-container">
        <form className="auth-card" onSubmit={handleSubmit}>
          <h1 className="auth-title">Crear cuenta</h1>

          <FormField label="Nombre" htmlFor="register-nombre" required>
            <Input
              type="text"
              name="nombre"
              placeholder="Tu nombre"
              value={nombre}
              onChange={(e) => setNombre(e.target.value)}
              required
            />
          </FormField>

          <FormField label="Email" htmlFor="register-email" required>
            <Input
              type="email"
              name="email"
              placeholder="tu@email.com"
              value={email}
              onChange={(e) => setEmail(e.target.value)}
              required
            />
          </FormField>

          <FormField label="Contraseña" htmlFor="register-password" required>
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
            {enviando ? 'Creando cuenta…' : 'Registrarme'}
          </Boton>

          <p className="auth-switch">
            ¿Ya tenés cuenta? <Link to="/login">Iniciá sesión</Link>
          </p>
        </form>
      </div>
    </div>
  );
}

export default RegisterPage;
