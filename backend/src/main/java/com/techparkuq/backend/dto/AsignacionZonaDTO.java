package com.techparkuq.backend.dto;

public class AsignacionZonaDTO {

    private Long empleadoId;
    private Long zonaId;

    public AsignacionZonaDTO() {
    }

    public AsignacionZonaDTO(Long empleadoId, Long zonaId) {
        this.empleadoId = empleadoId;
        this.zonaId = zonaId;
    }

    public Long getEmpleadoId() {
        return empleadoId;
    }

    public void setEmpleadoId(Long empleadoId) {
        this.empleadoId = empleadoId;
    }

    public Long getZonaId() {
        return zonaId;
    }

    public void setZonaId(Long zonaId) {
        this.zonaId = zonaId;
    }
}
