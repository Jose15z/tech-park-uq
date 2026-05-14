import { Link } from "react-router-dom";
import "../styles/HomePage.css";

function HomePage() {
  return (
    <main className="home-container">
      <section className="hero-card">
        <h1>Tech-Park UQ</h1>
        <p>
          Sistema de gestión inteligente para visitantes, atracciones, zonas,
          rutas, filas virtuales y reportes operativos del parque.
        </p>

        <div className="home-actions">
          <Link to="/atracciones" className="primary-button">
            Ver atracciones
          </Link>

          <Link to="/zonas" className="secondary-button">
            Ver zonas
          </Link>
        </div>
      </section>
    </main>
  );
}

export default HomePage;