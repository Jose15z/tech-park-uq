import { useEffect, useState } from "react";
import api from "../services/api";
import "../styles/FormPage.css";

function CrearAtraccionPage() {
    const [zonas, setZonas] = useState([]);
    const [mensaje, setMensaje] = useState("");
    const [error, setError] = useState("");

    const [formulario, setFormulario] = useState({
        nombre: "",
        tipo: "FAMILIAR",
        capacidadMaximaPorCiclo: "",
        alturaMinima: "",
        edadMinima: "",
        costoAdicional: "",
        visitantesAcumulados: 0,
        tiempoEspera: "",
        estado: "ACTIVA",
        motivoCierre: "",
        zonaId: "",
    });

    useEffect(() => {
        cargarZonas();
    }, []);

    const cargarZonas = async () => {
        try {
            const response = await api.get("/zonas");
            setZonas(response.data);
        } catch (error) {
            setError("No se pudieron cargar las zonas.");
        }
    };

    const manejarCambio = (event) => {
        setFormulario({
            ...formulario,
            [event.target.name]: event.target.value,
        });
    };

    const crearAtraccion = async (event) => {
        event.preventDefault();

        try {
            const payload = {
                nombre: formulario.nombre,
                tipo: formulario.tipo,
                capacidadMaximaPorCiclo: Number(formulario.capacidadMaximaPorCiclo),
                alturaMinima: Number(formulario.alturaMinima),
                edadMinima: Number(formulario.edadMinima),
                costoAdicional: Number(formulario.costoAdicional),
                visitantesAcumulados: 0,
                tiempoEspera: Number(formulario.tiempoEspera),
                estado: formulario.estado,
                motivoCierre: formulario.motivoCierre || null,
                zona: {
                    id: Number(formulario.zonaId),
                },
            };

            await api.post("/atracciones", payload);

            setMensaje("Atracción creada correctamente.");
            setError("");

            setFormulario({
                nombre: "",
                tipo: "FAMILIAR",
                capacidadMaximaPorCiclo: "",
                alturaMinima: "",
                edadMinima: "",
                costoAdicional: "",
                visitantesAcumulados: 0,
                tiempoEspera: "",
                estado: "ACTIVA",
                motivoCierre: "",
                zonaId: "",
            });
        } catch (error) {
            setError("No se pudo crear la atracción.");
            setMensaje("");
        }
    };

    return (
        <main className="form-page">
            <form className="form-card" onSubmit={crearAtraccion}>
                <h1>Crear atracción</h1>
                <p>Registra una nueva atracción dentro de una zona del parque.</p>

                {mensaje && <div className="success-message">{mensaje}</div>}
                {error && <div className="error-message">{error}</div>}

                <label>Nombre</label>
                <input name="nombre" value={formulario.nombre} onChange={manejarCambio} required />

                <label>Tipo</label>
                <select name="tipo" value={formulario.tipo} onChange={manejarCambio}>
                    <option value="ACUATICA">Acuática</option>
                    <option value="MECANICA_ALTURA">Mecánica de altura</option>
                    <option value="INFANTIL">Infantil</option>
                    <option value="FAMILIAR">Familiar</option>
                    <option value="EXTREMA">Extrema</option>
                </select>

                <label>Capacidad máxima por ciclo</label>
                <input type="number" name="capacidadMaximaPorCiclo" value={formulario.capacidadMaximaPorCiclo} onChange={manejarCambio} required />

                <label>Altura mínima</label>
                <input type="number" step="0.01" name="alturaMinima" value={formulario.alturaMinima} onChange={manejarCambio} required />

                <label>Edad mínima</label>
                <input type="number" name="edadMinima" value={formulario.edadMinima} onChange={manejarCambio} required />

                <label>Costo adicional</label>
                <input type="number" name="costoAdicional" value={formulario.costoAdicional} onChange={manejarCambio} required />

                <label>Tiempo de espera</label>
                <input type="number" name="tiempoEspera" value={formulario.tiempoEspera} onChange={manejarCambio} required />

                <label>Zona</label>
                <select name="zonaId" value={formulario.zonaId} onChange={manejarCambio} required>
                    <option value="">Selecciona una zona</option>
                    {zonas.map((zona) => (
                        <option key={zona.id} value={zona.id}>
                            {zona.nombre}
                        </option>
                    ))}
                </select>

                <button type="submit">Crear atracción</button>
            </form>
        </main>
    );
}

export default CrearAtraccionPage;