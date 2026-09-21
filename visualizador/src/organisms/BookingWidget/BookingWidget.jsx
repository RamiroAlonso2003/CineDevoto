import { useState, useEffect } from 'react';
import { useNavigate } from 'react-router-dom';
import { getShowtimesPorPelicula } from '../../api/showtimes';

function BookingWidget({ peliculaId }) {
  const navigate = useNavigate();
  const [fechaInicio] = useState(new Date());
  const [diaSeleccionado, setDiaSeleccionado] = useState(null);
  const [showtimes, setShowtimes] = useState([]);
  const [cargandoShowtimes, setCargandoShowtimes] = useState(false);

  // Generar 14 días desde hoy
  const generarDias = () => {
    const arrayDias = [];
    const nombresDias = ['Domingo', 'Lunes', 'Martes', 'Miércoles', 'Jueves', 'Viernes', 'Sábado'];
    const nombresMeses = ['Enero', 'Febrero', 'Marzo', 'Abril', 'Mayo', 'Junio', 'Julio', 'Agosto', 'Septiembre', 'Octubre', 'Noviembre', 'Diciembre'];

    for (let i = 0; i < 14; i++) {
      const fecha = new Date(fechaInicio);
      fecha.setDate(fechaInicio.getDate() + i);

      arrayDias.push({
        numeroDia: fecha.getDate(),
        dia: nombresDias[fecha.getDay()],
        mes: nombresMeses[fecha.getMonth()],
        fechaCompleta: new Date(fecha),
        formateado: `${fecha.getDate()}/${fecha.getMonth() + 1}/${fecha.getFullYear()}`
      });
    }

    return arrayDias;
  };

  const dias = generarDias();

  // Inicializar el día seleccionado con el día de hoy
  useEffect(() => {
    setDiaSeleccionado(dias[0]);
    // eslint-disable-next-line react-hooks/exhaustive-deps
  }, []);

  // Buscar funciones del día seleccionado
  useEffect(() => {
    if (!diaSeleccionado || !peliculaId) return;

    const f = diaSeleccionado.fechaCompleta;
    const fechaISO = `${f.getFullYear()}-${String(f.getMonth() + 1).padStart(2, '0')}-${String(f.getDate()).padStart(2, '0')}`;

    setCargandoShowtimes(true);
    getShowtimesPorPelicula(peliculaId, fechaISO)
      .then(setShowtimes)
      .catch(() => setShowtimes([]))
      .finally(() => setCargandoShowtimes(false));
  }, [diaSeleccionado, peliculaId]);

  const formatearHora = (inicio) => {
    const fecha = new Date(inicio);
    return fecha.toLocaleTimeString('es-AR', { hour: '2-digit', minute: '2-digit' });
  };

  if (!peliculaId) return null;

  return (
    <div>
      <div className="rail" role="group" aria-label="Elegir día">
        {dias.map((infoDia, indice) => (
          <button
            key={indice}
            type="button"
            className="daypill"
            aria-pressed={diaSeleccionado?.formateado === infoDia.formateado}
            onClick={() => setDiaSeleccionado(infoDia)}
          >
            <span className="daypill__dow">{infoDia.dia}</span>
            <span className="daypill__num">{infoDia.numeroDia}</span>
            <span className="daypill__mon">{infoDia.mes}</span>
          </button>
        ))}
      </div>

      <div className="times">
        {cargandoShowtimes && <p>Buscando funciones…</p>}
        {!cargandoShowtimes && showtimes.length === 0 && <p>No hay funciones ese día.</p>}
        {!cargandoShowtimes && showtimes.map((s) => (
          <button
            key={s.showtimeId}
            type="button"
            className="time"
            onClick={() => navigate(`/showtime/${s.showtimeId}/asientos`)}
          >
            {formatearHora(s.inicio)}
          </button>
        ))}
      </div>
    </div>
  );
}

export default BookingWidget;
