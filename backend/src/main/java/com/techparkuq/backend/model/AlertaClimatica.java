package com.techparkuq.backend.model;

import com.techparkuq.backend.enums.TipoAlertaClimatica;
import jakarta.persistence.*;

@Entity
@Table(name = "alertas_climaticas")
public class AlertaClimatica {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private TipoAlertaClimatica tipoAlerta;

    @Column(nullable = false)
    private boolean activa;

    public AlertaClimatica() {
    }

    public AlertaClimatica(TipoAlertaClimatica tipoAlerta, boolean activa) {
        this.tipoAlerta = tipoAlerta;
        this.activa = activa;
    }

    public Long getId() {
        return id;
    }

    public TipoAlertaClimatica getTipoAlerta() {
        return tipoAlerta;
    }

    public void setTipoAlerta(TipoAlertaClimatica tipoAlerta) {
        this.tipoAlerta = tipoAlerta;
    }

    public boolean isActiva() {
        return activa;
    }

    public void setActiva(boolean activa) {
        this.activa = activa;
    }
}
