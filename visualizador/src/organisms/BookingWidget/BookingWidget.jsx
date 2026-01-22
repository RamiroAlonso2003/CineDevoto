import { useState, useEffect } from 'react';
import './BookingWidget.css';

function BookingWidget() {
  const [fechaInicio, setFechaInicio] = useState(new Date());
  const [diaSeleccionado, setDiaSeleccionado] = useState(null);
  const [animando, setAnimando] = useState(false);

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
  }, []);

  // Manejar selección de día
  const manejarClickDia = (infoDia) => {
    setDiaSeleccionado(infoDia);
    console.log('Día seleccionado:', infoDia);
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

  // Effect para actualizar cuando cambia diaSeleccionado
  useEffect(() => {
    if (diaSeleccionado) {
      console.log('Día actualizado:', diaSeleccionado.formateado);
      // Aquí puedes hacer fetch de horarios, películas, etc.
    }
  }, [diaSeleccionado]);

  return (
    <div className="booking-wrapper">
      <div className="booking-widget">
        <p className="booking-instruction">
          Comprar Boletos
        </p>

      <div className="booking-content">
        <p className="booking-subtitle">
          Seleccione el DÍA y la PELÍCULA de su preferencia
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
      </div>
      </div>
    </div>
  );
}

export default BookingWidget;
