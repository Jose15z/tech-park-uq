package com.techparkuq.backend.dto;

public class IngresoAtraccionDTO {

    private Long visitanteId;
    private Long atraccionId;

    public IngresoAtraccionDTO() {
    }

    public IngresoAtraccionDTO(Long visitanteId, Long atraccionId) {
        this.visitanteId = visitanteId;
        this.atraccionId = atraccionId;
    }

    public Long getVisitanteId() {
        return visitanteId;
    }

    public void setVisitanteId(Long visitanteId) {
        this.visitanteId = visitanteId;
    }

    public Long getAtraccionId() {
        return atraccionId;
    }

    public void setAtraccionId(Long atraccionId) {
        this.atraccionId = atraccionId;
    }
}