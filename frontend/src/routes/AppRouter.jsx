import { BrowserRouter, Route, Routes } from "react-router-dom";
import Navbar from "../components/Navbar";
import HomePage from "../pages/HomePage";
import AtraccionesPage from "../pages/AtraccionesPage";
import ZonasPage from "../pages/ZonasPage";
import LoginPage from "../pages/LoginPage";
import RegistroVisitantePage from "../pages/RegistroVisitantePage";
import AdminDashboardPage from "../pages/AdminDashboardPage";
import VisitanteDashboardPage from "../pages/VisitanteDashboardPage";
import OperadorDashboardPage from "../pages/OperadorDashboardPage";
import CrearAtraccionPage from "../pages/CrearAtraccionPage";

function AppRouter() {
    return (
        <BrowserRouter>
            <Navbar />

            <Routes>
                <Route path="/" element={<HomePage />} />
                <Route path="/login" element={<LoginPage />} />
                <Route path="/registro" element={<RegistroVisitantePage />} />

                <Route path="/visitante" element={<VisitanteDashboardPage />} />
                <Route path="/operador" element={<OperadorDashboardPage />} />
                <Route path="/admin" element={<AdminDashboardPage />} />

                <Route path="/admin/atracciones/crear" element={<CrearAtraccionPage />} />

                <Route path="/atracciones" element={<AtraccionesPage />} />
                <Route path="/zonas" element={<ZonasPage />} />
            </Routes>
        </BrowserRouter>
    );
}

export default AppRouter;