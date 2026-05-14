import { BrowserRouter, Route, Routes } from "react-router-dom";
import Navbar from "../components/Navbar";
import HomePage from "../pages/HomePage";
import AtraccionesPage from "../pages/AtraccionesPage";
import ZonasPage from "../pages/ZonasPage";

function AppRouter() {
  return (
    <BrowserRouter>
      <Navbar />

      <Routes>
        <Route path="/" element={<HomePage />} />
        <Route path="/atracciones" element={<AtraccionesPage />} />
        <Route path="/zonas" element={<ZonasPage />} />
      </Routes>
    </BrowserRouter>
  );
}

export default AppRouter;