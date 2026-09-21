import { useState, useEffect } from 'react';
import { useNavigate } from 'react-router-dom';
import { getShowtimesPorPelicula } from '../../api/showtimes';
import './BookingWidget.css';

function BookingWidget({ peliculaId }) {
  const navigate = useNavigate();
  const [fechaInicio, setFechaInicio] = useState(new Date());
  const [diaSeleccionado, setDiaSeleccionado] = useState(null);
  const [animando, setAnimando] = useState(false);
  const [showtimes, setShowtimes] = useState([]);
  const [cargandoShowtimes, setCargandoShowtimes] = useState(false);

  // Generar 7 días desde fechaInicio
  const generarDias = () => {
    const arrayDias = [];
    const nombresDias = ['Domingo', 'Lunes', 'Martes', 'Miércoles', 'Jueves', 'Viernes', 'Sábado'];
    const nombresMeses = ['Enero', 'Febrero', 'Marzo', 'Abril', 'Mayo', 'Junio', 'Julio', 'Agosto', 'Septiembre', 'Octubre', 'Noviembre', 'Diciembre'];

    for (let i = 0; i < 7; i++) {
      const fecha = new Date(fechaInicio);
      fecha.setDate(fechaInicio.getDate() + i);

      arrayDias.push({
        indice: i,
        numeroDia: fecha.getDate(),
        dia: nombresDias[fecha.getDay()],
        mes: nombresMeses[fecha.getMonth()],
        año: fecha.getFullYear(),
        fechaCompleta: new Date(fecha),
        formateado: `${fecha.getDate()}/${fecha.getMonth() + 1}/${fecha.getFullYear()}`
      });
    }

    return arrayDias;
  };

  const dias = generarDias();

  // Inicializar el día seleccionado con el día de hoy
  useEffect(() => {
    if (!diaSeleccionado && dias.length > 0) {
      setDiaSeleccionado(dias[0]);
    }
    // eslint-disable-next-line react-hooks/exhaustive-deps
  }, []);

  // Manejar selección de día
  const manejarClickDia = (infoDia) => {
    setDiaSeleccionado(infoDia);
  };

  // Navegar entre días
  const desplazarDias = (direccion) => {
    setAnimando(true);

    setTimeout(() => {
      const nuevaFecha = new Date(fechaInicio);
      if (direccion === 'izquierda') {
        nuevaFecha.setDate(fechaInicio.getDate() - 1);
      } else {
        nuevaFecha.setDate(fechaInicio.getDate() + 1);
      }
      setFechaInicio(nuevaFecha);
      // No resetear selección, mantener el día seleccionado

      setTimeout(() => setAnimando(false), 50);
    }, 150);
  };

  // Buscar funciones del día seleccionado (solo si hay una película puntual)
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

  return (
    <div className="booking-wrapper">
      <div className="booking-widget">
        <p className="booking-instruction">
          Comprar Boletos
        </p>

      <div className="booking-content">
        <p className="booking-subtitle">
          Seleccione el DÍA {peliculaId ? 'y el horario' : 'y la PELÍCULA'} de su preferencia
        </p>

        <div className="day-selector">
          <button
            className="day-arrow"
            onClick={() => desplazarDias('izquierda')}
          >
            ‹
          </button>

          <div className={`days-container ${animando ? 'animando' : ''}`}>
            {dias.map((infoDia, indice) => (
              <button
                key={indice}
                className={`day-card ${diaSeleccionado?.formateado === infoDia.formateado ? 'day-card-active' : ''}`}
                onClick={() => manejarClickDia(infoDia)}
              >
                <span className="day-weekday">{infoDia.dia}</span>
                <span className="day-date">{infoDia.numeroDia}</span>
                <span className="day-month">{infoDia.mes}</span>
              </button>
            ))}
          </div>

          <button
            className="day-arrow"
            onClick={() => desplazarDias('derecha')}
          >
            ›
          </button>
        </div>

        {peliculaId && (
          <div className="showtime-list">
            {cargandoShowtimes && <p className="showtime-estado">Buscando funciones…</p>}
            {!cargandoShowtimes && showtimes.length === 0 && (
              <p className="showtime-estado">No hay funciones ese día.</p>
            )}
            {!cargandoShowtimes && showtimes.map((s) => (
              <button
                key={s.showtimeId}
                type="button"
                className="showtime-pill"
                onClick={() => navigate(`/showtime/${s.showtimeId}/asientos`)}
              >
                {formatearHora(s.inicio)}
              </button>
            ))}
          </div>
        )}
      </div>
      </div>
    </div>
  );
}

export default BookingWidget;
