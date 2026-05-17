import { Link } from "react-router-dom";
import "../styles/DashboardPage.css";

function AdminDashboardPage() {
    const usuario = JSON.parse(localStorage.getItem("usuario"));

    return (
        <main className="dashboard-page">
            <h1>Panel de administración</h1>
            <p>Bienvenido, {usuario?.nombre}. Desde aquí puedes gestionar el parque.</p>

            <section className="dashboard-grid">
                <Link to="/admin/atracciones/crear" className="dashboard-card">
                    <h2>Crear atracción</h2>
                    <p>Registrar una nueva atracción en una zona.</p>
                </Link>

                <Link to="/atracciones" className="dashboard-card">
                    <h2>Ver atracciones</h2>
                    <p>Consultar atracciones existentes.</p>
                </Link>

                <Link to="/zonas" className="dashboard-card">
                    <h2>Ver zonas</h2>
                    <p>Consultar zonas del parque.</p>
                </Link>
            </section>
        </main>
    );
}

export default AdminDashboardPage;