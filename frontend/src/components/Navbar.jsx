import { Link } from "react-router-dom";
import "../styles/Navbar.css";

function Navbar() {
  return (
    <nav className="navbar">
      <Link to="/" className="navbar-logo">
        Tech-Park UQ
      </Link>

      <div className="navbar-links">
        <Link to="/atracciones">Atracciones</Link>
        <Link to="/zonas">Zonas</Link>
      </div>
    </nav>
  );
}

export default Navbar;