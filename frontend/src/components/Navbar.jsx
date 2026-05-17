import { Link, useNavigate } from "react-router-dom";
import "../styles/Navbar.css";

function Navbar() {
    const navigate = useNavigate();
    const usuario = JSON.parse(localStorage.getItem("usuario"));

    const cerrarSesion = () => {
        localStorage.removeItem("usuario");
        navigate("/login");
    };

    return (
        <nav className="navbar">
            <Link to="/" className="navbar-logo">
                Tech-Park UQ
            </Link>

            <div className="navbar-links">
                <Link to="/atracciones">Atracciones</Link>
                <Link to="/zonas">Zonas</Link>

                {!usuario && <Link to="/login">Login</Link>}

                {usuario?.rol === "ADMINISTRADOR" && <Link to="/admin">Admin</Link>}
                {usuario?.rol === "VISITANTE" && <Link to="/visitante">Visitante</Link>}
                {usuario?.rol === "OPERADOR" && <Link to="/operador">Operador</Link>}

                {usuario && (
                    <button className="logout-button" onClick={cerrarSesion}>
                        Salir
                    </button>
                )}
            </div>
        </nav>
    );
}

export default Navbar;