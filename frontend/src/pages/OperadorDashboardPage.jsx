import "../styles/DashboardPage.css";

function OperadorDashboardPage() {
    const usuario = JSON.parse(localStorage.getItem("usuario"));

    return (
        <main className="dashboard-page">
            <h1>Panel de operador</h1>
            <p>Bienvenido, {usuario?.nombre}. Aquí podrás gestionar atracciones de tu zona asignada.</p>

            <section className="dashboard-grid">
                <div className="dashboard-card">
                    <h2>Validar acceso</h2>
                    <p>Próximamente: validar visitantes en atracciones.</p>
                </div>

                <div className="dashboard-card">
                    <h2>Fila virtual</h2>
                    <p>Próximamente: procesar visitantes en cola.</p>
                </div>
            </section>
        </main>
    );
}

export default OperadorDashboardPage;