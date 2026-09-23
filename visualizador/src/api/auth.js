import request from './http';

export function login(email, password) {
  return request('/auth/login', { method: 'POST', body: { email, password } });
}

export function register(email, nombre, password) {
  return request('/auth/register', { method: 'POST', body: { email, nombre, password } });
}
