import { useState } from "react";
import { useNavigate } from "react-router-dom";
import api from "../services/api";
import "../styles/AuthPage.css";

function RegistroVisitantePage() {
    const navigate = useNavigate();

    const [formulario, setFormulario] = useState({
        nombre: "",
        documento: "",
        email: "",
        password: "",
        edad: "",
        estatura: "",
        saldoVirtual: "",
        fotoUrl: "",
    });

    const [error, setError] = useState("");

    const manejarCambio = (event) => {
        setFormulario({
            ...formulario,
            [event.target.name]: event.target.value,
        });
    };

    const registrar = async (event) => {
        event.preventDefault();

        try {
            const payload = {
                ...formulario,
                edad: Number(formulario.edad),
                estatura: Number(formulario.estatura),
                saldoVirtual: Number(formulario.saldoVirtual),
            };

            const response = await api.post("/auth/registro/visitante", payload);
            localStorage.setItem("usuario", JSON.stringify(response.data));
            navigate("/visitante");
        } catch (error) {
            setError("No se pudo registrar el visitante.");
        }
    };

    return (
        <main className="auth-container">
            <form className="auth-card" onSubmit={registrar}>
                <h1>Registro de visitante</h1>
                <p>Crea tu pase digital para ingresar al parque.</p>

                {error && <div className="auth-error">{error}</div>}

                <label>Nombre</label>
                <input name="nombre" value={formulario.nombre} onChange={manejarCambio} required />

                <label>Documento</label>
                <input name="documento" value={formulario.documento} onChange={manejarCambio} required />

                <label>Correo</label>
                <input type="email" name="email" value={formulario.email} onChange={manejarCambio} required />

                <label>Contraseña</label>
                <input type="password" name="password" value={formulario.password} onChange={manejarCambio} required />

                <label>Edad</label>
                <input type="number" name="edad" value={formulario.edad} onChange={manejarCambio} required />

                <label>Estatura</label>
                <input type="number" step="0.01" name="estatura" value={formulario.estatura} onChange={manejarCambio} required />

                <label>Saldo virtual</label>
                <input type="number" name="saldoVirtual" value={formulario.saldoVirtual} onChange={manejarCambio} required />

                <label>Foto URL</label>
                <input name="fotoUrl" value={formulario.fotoUrl} onChange={manejarCambio} />

                <button type="submit">Registrarme</button>
            </form>
        </main>
    );
}

export default RegistroVisitantePage;