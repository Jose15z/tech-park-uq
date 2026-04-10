package com.techparkuq.backend.model;

import java.time.LocalDateTime;

public class RegistroVisita {

    private Long visitanteId;
    private String nombreVisitante;
    private Long atraccionId;
    private String nombreAtraccion;
    private LocalDateTime fechaHora;

    public RegistroVisita(Long visitanteId, String nombreVisitante,
                          Long atraccionId, String nombreAtraccion,
                          LocalDateTime fechaHora) {
        this.visitanteId = visitanteId;
        this.nombreVisitante = nombreVisitante;
        this.atraccionId = atraccionId;
        this.nombreAtraccion = nombreAtraccion;
        this.fechaHora = fechaHora;
    }

    public Long getVisitanteId() {
        return visitanteId;
    }

    public String getNombreVisitante() {
        return nombreVisitante;
    }

    public Long getAtraccionId() {
        return atraccionId;
    }

    public String getNombreAtraccion() {
        return nombreAtraccion;
    }

    public LocalDateTime getFechaHora() {
        return fechaHora;
    }
}