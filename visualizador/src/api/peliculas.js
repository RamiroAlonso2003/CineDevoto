import request from './http';

export function getPeliculas() {
  return request('/peliculas');
}

export function getPeliculaPorId(id) {
  return request(`/peliculas/${id}`);
}
