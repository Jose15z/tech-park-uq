import { useEffect, useState } from "react";
import api from "../services/api";
import "../styles/TablePage.css";

function ZonasPage() {
  const [zonas, setZonas] = useState([]);
  const [cargando, setCargando] = useState(true);
  const [error, setError] = useState("");

  useEffect(() => {
    cargarZonas();
  }, []);

  const cargarZonas = async () => {
    try {
      const response = await api.get("/zonas");
      setZonas(response.data);
      setError("");
    } catch (error) {
      setError("No se pudieron cargar las zonas.");
    } finally {
      setCargando(false);
    }
  };

  if (cargando) {
    return <p className="page-message">Cargando zonas...</p>;
  }

  return (
    <main className="table-page">
      <h1>Zonas</h1>
      <p>Zonas registradas dentro del parque Tech-Park UQ.</p>

      {error && <p className="error-message">{error}</p>}

      <div className="table-wrapper">
        <table>
          <thead>
            <tr>
              <th>Nombre</th>
              <th>Capacidad máxima</th>
              <th>Cantidad de atracciones</th>
            </tr>
          </thead>

          <tbody>
            {zonas.map((zona) => (
              <tr key={zona.id}>
                <td>{zona.nombre}</td>
                <td>{zona.capacidadMaxima}</td>
                <td>{zona.atracciones ? zona.atracciones.length : 0}</td>
              </tr>
            ))}
          </tbody>
        </table>
      </div>
    </main>
  );
}

export default ZonasPage;