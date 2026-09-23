import { createContext, useCallback, useContext, useState } from 'react';
import { login as loginRequest, register as registerRequest } from '../api/auth';
import { getToken, setToken as guardarToken, clearToken } from '../api/http';

const AuthContext = createContext(null);

function emailDelToken(token) {
  try {
    const payload = JSON.parse(atob(token.split('.')[1]));
    return payload.sub || null;
  } catch {
    return null;
  }
}

export function AuthProvider({ children }) {
  const [token, setToken] = useState(() => getToken());

  const login = useCallback(async (email, password) => {
    const { token: nuevoToken } = await loginRequest(email, password);
    guardarToken(nuevoToken);
    setToken(nuevoToken);
  }, []);

  const register = useCallback(async (email, nombre, password) => {
    const { token: nuevoToken } = await registerRequest(email, nombre, password);
    guardarToken(nuevoToken);
    setToken(nuevoToken);
  }, []);

  const logout = useCallback(() => {
    clearToken();
    setToken(null);
  }, []);

  const value = {
    token,
    email: token ? emailDelToken(token) : null,
    isAuthenticated: !!token,
    login,
    register,
    logout,
  };

  return <AuthContext.Provider value={value}>{children}</AuthContext.Provider>;
}

export function useAuth() {
  const ctx = useContext(AuthContext);
  if (!ctx) throw new Error('useAuth debe usarse dentro de <AuthProvider>');
  return ctx;
}
