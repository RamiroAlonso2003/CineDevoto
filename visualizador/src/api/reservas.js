import request from './http';

export function crearReserva(showtimeId, asientos) {
  return request('/reservas', {
    method: 'POST',
    auth: true,
    body: {
      showtime: { showtimeId },
      asientos,
    },
  });
}
