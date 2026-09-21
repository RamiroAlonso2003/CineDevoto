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
    <div>
      <Navbar />
      <div className="authpage">
        <div>
          <h1 className="authpage__title">Creá tu cuenta</h1>
          <p className="authpage__blurb">Registrate para elegir asientos y guardar tus reservas.</p>
        </div>

        <form className="authcard" onSubmit={handleSubmit}>
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

          <FormField label="Contraseña" htmlFor="register-password" required last>
            <Input
              type="password"
              name="password"
              placeholder="••••••••"
              value={password}
              onChange={(e) => setPassword(e.target.value)}
              required
            />
          </FormField>

          {error && <p className="authcard__error">{error}</p>}

          <Boton type="submit" variant="primary" block disabled={enviando}>
            {enviando ? 'Creando cuenta…' : 'Registrarme'}
          </Boton>

          <p className="authcard__switch">
            ¿Ya tenés cuenta? <Link to="/login">Iniciá sesión</Link>
          </p>
        </form>
      </div>
    </div>
  );
}

export default RegisterPage;
