import { useEffect, useMemo, useState } from 'react';
import { useNavigate, useParams } from 'react-router-dom';
import Navbar from '../../organisms/navbar/Navbar';
import Boton from '../../Atoms/boton/boton';
import { getMapaAsientos } from '../../api/showtimes';
import { crearReserva } from '../../api/reservas';
import './SeatSelectionPage.css';

function claveAsiento(fila, numero) {
  return `${fila}${numero}`;
}

function SeatSelectionPage() {
  const { showtimeId } = useParams();
  const navigate = useNavigate();

  const [asientos, setAsientos] = useState([]);
  const [seleccionados, setSeleccionados] = useState(new Set());
  const [cargando, setCargando] = useState(true);
  const [error, setError] = useState(null);
  const [confirmando, setConfirmando] = useState(false);
  const [reservaConfirmada, setReservaConfirmada] = useState(false);

  const cargarAsientos = () => {
    setCargando(true);
    setError(null);
    getMapaAsientos(showtimeId)
      .then(setAsientos)
      .catch((err) => setError(err.message))
      .finally(() => setCargando(false));
  };

  useEffect(cargarAsientos, [showtimeId]);

  const filas = useMemo(() => {
    const porFila = new Map();
    for (const asiento of asientos) {
      if (!porFila.has(asiento.fila)) porFila.set(asiento.fila, []);
      porFila.get(asiento.fila).push(asiento);
    }
    return Array.from(porFila.entries())
      .sort(([a], [b]) => a.localeCompare(b))
      .map(([fila, lista]) => [fila, lista.sort((a, b) => a.numero - b.numero)]);
  }, [asientos]);

  const toggleAsiento = (asiento) => {
    if (asiento.estado === 'OCUPADO') return;
    const clave = claveAsiento(asiento.fila, asiento.numero);
    setSeleccionados((prev) => {
      const nuevo = new Set(prev);
      if (nuevo.has(clave)) {
        nuevo.delete(clave);
      } else {
        nuevo.add(clave);
      }
      return nuevo;
    });
  };

  const listaSeleccionados = useMemo(() => {
    return asientos
      .filter((a) => seleccionados.has(claveAsiento(a.fila, a.numero)))
      .sort((a, b) => (a.fila + a.numero).localeCompare(b.fila + b.numero));
  }, [asientos, seleccionados]);

  const confirmarReserva = async () => {
    if (listaSeleccionados.length === 0) return;
    setConfirmando(true);
    setError(null);
    try {
      await crearReserva(
        showtimeId,
        listaSeleccionados.map((a) => ({ fila: a.fila, numero: a.numero }))
      );
      setReservaConfirmada(true);
    } catch (err) {
      setError('No pudimos confirmar la reserva: ' + err.message);
      cargarAsientos();
      setSeleccionados(new Set());
    } finally {
      setConfirmando(false);
    }
  };

  if (reservaConfirmada) {
    return (
      <div className="seat-page">
        <Navbar />
        <div className="seat-confirmacion">
          <h1>¡Reserva confirmada!</h1>
          <p>
            Asientos reservados:{' '}
            {listaSeleccionados.map((a) => `${a.fila}${a.numero}`).join(', ')}
          </p>
          <Boton variant="primary" size="md" onClick={() => navigate('/')}>
            Volver al inicio
          </Boton>
        </div>
      </div>
    );
  }

  return (
    <div className="seat-page">
      <Navbar />

      <div className="seat-layout">
        <div className="seat-main">
          <h1 className="seat-title">Elegí tus asientos</h1>

          {cargando && <p className="seat-estado">Cargando mapa de asientos…</p>}
          {error && <p className="seat-estado seat-error">{error}</p>}

          {!cargando && !error && (
            <>
              <div className="seat-screen">
                <div className="seat-screen-bar" />
                <span>Pantalla</span>
              </div>

              <div className="seat-grid">
                {filas.map(([fila, lista]) => (
                  <div className="seat-row" key={fila}>
                    <span className="seat-row-label">{fila}</span>
                    {lista.map((asiento) => {
                      const clave = claveAsiento(asiento.fila, asiento.numero);
                      const estaSeleccionado = seleccionados.has(clave);
                      const claseEstado = estaSeleccionado
                        ? 'seleccionado'
                        : asiento.estado === 'OCUPADO'
                        ? 'ocupado'
                        : 'disponible';
                      return (
                        <button
                          key={clave}
                          type="button"
                          className={`seat-btn seat-${claseEstado}`}
                          disabled={asiento.estado === 'OCUPADO'}
                          onClick={() => toggleAsiento(asiento)}
                          aria-label={`Asiento ${asiento.fila}${asiento.numero}, ${asiento.estado.toLowerCase()}`}
                        >
                          {asiento.numero}
                        </button>
                      );
                    })}
                    <span className="seat-row-label">{fila}</span>
                  </div>
                ))}
              </div>

              <div className="seat-legend">
                <span className="seat-legend-item"><i className="seat-swatch disponible" /> Disponible</span>
                <span className="seat-legend-item"><i className="seat-swatch seleccionado" /> Seleccionado</span>
                <span className="seat-legend-item"><i className="seat-swatch ocupado" /> Ocupado</span>
              </div>
            </>
          )}
        </div>

        <aside className="seat-sidebar">
          <h2>Tu selección</h2>

          {listaSeleccionados.length === 0 ? (
            <p className="seat-sidebar-vacio">Tocá un asiento disponible para agregarlo.</p>
          ) : (
            <ul className="seat-sidebar-lista">
              {listaSeleccionados.map((a) => (
                <li key={claveAsiento(a.fila, a.numero)}>Asiento {a.fila}{a.numero}</li>
              ))}
            </ul>
          )}

          <p className="seat-sidebar-total">{listaSeleccionados.length} asiento(s) seleccionado(s)</p>

          <Boton
            variant="primary"
            size="md"
            disabled={listaSeleccionados.length === 0 || confirmando}
            onClick={confirmarReserva}
          >
            {confirmando ? 'Confirmando…' : 'Confirmar reserva'}
          </Boton>
        </aside>
      </div>
    </div>
  );
}

export default SeatSelectionPage;
