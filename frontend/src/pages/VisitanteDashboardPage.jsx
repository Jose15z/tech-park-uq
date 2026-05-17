import { Link } from "react-router-dom";
import "../styles/DashboardPage.css";

function VisitanteDashboardPage() {
    const usuario = JSON.parse(localStorage.getItem("usuario"));

    return (
        <main className="dashboard-page">
            <h1>Panel de visitante</h1>
            <p>Bienvenido, {usuario?.nombre}. Aquí puedes consultar atracciones y rutas.</p>

            <section className="dashboard-grid">
                <Link to="/atracciones" className="dashboard-card">
                    <h2>Ver atracciones</h2>
                    <p>Consulta disponibilidad, tiempos de espera y restricciones.</p>
                </Link>

                <Link to="/zonas" className="dashboard-card">
                    <h2>Ver zonas</h2>
                    <p>Explora las zonas disponibles del parque.</p>
                </Link>
            </section>
        </main>
    );
}

export default VisitanteDashboardPage;