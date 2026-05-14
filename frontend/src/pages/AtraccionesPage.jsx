import { useEffect, useState } from "react";
import api from "../services/api";
import "../styles/TablePage.css";

function AtraccionesPage() {
  const [atracciones, setAtracciones] = useState([]);
  const [cargando, setCargando] = useState(true);
  const [error, setError] = useState("");

  useEffect(() => {
    cargarAtracciones();
  }, []);

  const cargarAtracciones = async () => {
    try {
      const response = await api.get("/atracciones");
      setAtracciones(response.data);
      setError("");
    } catch (error) {
      setError("No se pudieron cargar las atracciones. Revisa que el backend esté corriendo.");
    } finally {
      setCargando(false);
    }
  };

  if (cargando) {
    return <p className="page-message">Cargando atracciones...</p>;
  }

  return (
      <main className="table-page">
        <h1>Atracciones</h1>
        <p>Listado general de atracciones registradas en el parque.</p>

        {error && <p className="error-message">{error}</p>}

        <div className="table-wrapper">
          <table>
            <thead>
            <tr>
              <th>Nombre</th>
              <th>Tipo</th>
              <th>Estado</th>
              <th>Altura mínima</th>
              <th>Edad mínima</th>
              <th>Tiempo espera</th>
            </tr>
            </thead>

            <tbody>
            {atracciones.map((atraccion) => (
                <tr key={atraccion.id}>
                  <td>{atraccion.nombre}</td>
                  <td>{atraccion.tipo}</td>
                  <td>{atraccion.estado}</td>
                  <td>{atraccion.alturaMinima} m</td>
                  <td>{atraccion.edadMinima} años</td>
                  <td>{atraccion.tiempoEspera} min</td>
                </tr>
            ))}
            </tbody>
          </table>
        </div>
      </main>
  );
}

export default AtraccionesPage;
