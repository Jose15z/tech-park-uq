import { useState } from "react";
import { Link, useNavigate } from "react-router-dom";
import api from "../services/api";
import "../styles/AuthPage.css";

function LoginPage() {
    const navigate = useNavigate();

    const [formulario, setFormulario] = useState({
        email: "",
        password: "",
    });

    const [error, setError] = useState("");

    const manejarCambio = (event) => {
        setFormulario({
            ...formulario,
            [event.target.name]: event.target.value,
        });
    };

    const iniciarSesion = async (event) => {
        event.preventDefault();

        try {
            const response = await api.post("/auth/login", formulario);
            const usuario = response.data;

            localStorage.setItem("usuario", JSON.stringify(usuario));

            if (usuario.rol === "ADMINISTRADOR") {
                navigate("/admin");
            } else if (usuario.rol === "OPERADOR") {
                navigate("/operador");
            } else {
                navigate("/visitante");
            }
        } catch (error) {
            setError("Correo o contraseña incorrectos.");
        }
    };

    return (
        <main className="auth-container">
            <form className="auth-card" onSubmit={iniciarSesion}>
                <h1>Iniciar sesión</h1>
                <p>Accede como visitante, operador o administrador.</p>

                {error && <div className="auth-error">{error}</div>}

                <label>Correo</label>
                <input
                    type="email"
                    name="email"
                    value={formulario.email}
                    onChange={manejarCambio}
                    required
                />

                <label>Contraseña</label>
                <input
                    type="password"
                    name="password"
                    value={formulario.password}
                    onChange={manejarCambio}
                    required
                />

                <button type="submit">Ingresar</button>

                <span>
          ¿No tienes cuenta? <Link to="/registro">Regístrate como visitante</Link>
        </span>
            </form>
        </main>
    );
}

export default LoginPage;