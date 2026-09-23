import request from './http';

export function getShowtimesPorPelicula(peliculaId, fecha) {
  const params = new URLSearchParams({ peliculaId });
  if (fecha) params.set('fecha', fecha);
  return request(`/showtimes?${params.toString()}`);
}

export function getMapaAsientos(showtimeId) {
  return request(`/showtimes/${showtimeId}/asientos`);
}
