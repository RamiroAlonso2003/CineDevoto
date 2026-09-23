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
      <div>
        <Navbar />
        <div className="page-wrap seatpage__confirmacion">
          <p className="eyebrow">Listo</p>
          <h1 className="seatpage__confirmacion-title">¡Reserva confirmada!</h1>
          <p className="seatpage__confirmacion-copy">
            Asientos reservados:{' '}
            {listaSeleccionados.map((a) => `${a.fila}${a.numero}`).join(', ')}
          </p>
          <Boton variant="primary" onClick={() => navigate('/')}>Volver al inicio</Boton>
        </div>
      </div>
    );
  }

  return (
    <div>
      <Navbar />

      <div className="seatpage">
        <div className="seatpage__map">
          {cargando && <p>Cargando mapa de asientos…</p>}
          {error && <p className="seatpage__error">{error}</p>}

          {!cargando && !error && (
            <>
              <div className="screen" />
              <p className="screen__cap">Pantalla</p>

              <div className="seatrows">
                {filas.map(([fila, lista]) => (
                  <div className="seatrow" key={fila}>
                    <span className="seatrow__id">{fila}</span>
                    <div className="seatrow__seats">
                      {lista.map((asiento) => {
                        const clave = claveAsiento(asiento.fila, asiento.numero);
                        const estaSeleccionado = seleccionados.has(clave);
                        return (
                          <button
                            key={clave}
                            type="button"
                            className="seat"
                            aria-pressed={estaSeleccionado}
                            disabled={asiento.estado === 'OCUPADO'}
                            onClick={() => toggleAsiento(asiento)}
                            aria-label={`Asiento ${asiento.fila}${asiento.numero}, ${asiento.estado.toLowerCase()}`}
                          >
                            {asiento.numero}
                          </button>
                        );
                      })}
                    </div>
                  </div>
                ))}
              </div>

              <div className="legend">
                <span className="legend__item">
                  <button type="button" className="seat legend__chip" disabled={false} aria-pressed="false" tabIndex={-1} aria-hidden="true" />
                  Disponible
                </span>
                <span className="legend__item">
                  <button type="button" className="seat legend__chip" aria-pressed="true" tabIndex={-1} aria-hidden="true" />
                  Seleccionado
                </span>
                <span className="legend__item">
                  <button type="button" className="seat legend__chip" disabled tabIndex={-1} aria-hidden="true" />
                  Ocupado
                </span>
              </div>
            </>
          )}
        </div>

        <aside className="seatpage__panel">
          <h2 className="eyebrow">Tu selección</h2>

          {listaSeleccionados.length === 0 ? (
            <p className="seatpage__vacio">Tocá un asiento disponible para agregarlo.</p>
          ) : (
            <div>
              {listaSeleccionados.map((a) => (
                <div className="summary__row" key={claveAsiento(a.fila, a.numero)}>
                  <span>Asiento {a.fila}{a.numero}</span>
                </div>
              ))}
            </div>
          )}

          <div className="summary__total">
            <span>Total</span>
            <span>{listaSeleccionados.length} asiento(s)</span>
          </div>

          <Boton
            variant="primary"
            block
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
