package com.techparkuq.backend.dto;

import com.techparkuq.backend.enums.TipoAlertaClimatica;

public class AlertaClimaticaDTO {

    private TipoAlertaClimatica tipoAlerta;
    private boolean activa;

    public AlertaClimaticaDTO() {
    }

    public AlertaClimaticaDTO(TipoAlertaClimatica tipoAlerta, boolean activa) {
        this.tipoAlerta = tipoAlerta;
        this.activa = activa;
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
