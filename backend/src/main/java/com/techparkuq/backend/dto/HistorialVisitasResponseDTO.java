package com.techparkuq.backend.dto;

import java.util.List;

public class HistorialVisitasResponseDTO {

    private Long visitanteId;
    private String nombreVisitante;
    private int cantidadVisitas;
    private List<String> visitas;

    public HistorialVisitasResponseDTO() {
    }

    public HistorialVisitasResponseDTO(Long visitanteId, String nombreVisitante, int cantidadVisitas, List<String> visitas) {
        this.visitanteId = visitanteId;
        this.nombreVisitante = nombreVisitante;
        this.cantidadVisitas = cantidadVisitas;
        this.visitas = visitas;
    }

    public Long getVisitanteId() {
        return visitanteId;
    }

    public void setVisitanteId(Long visitanteId) {
        this.visitanteId = visitanteId;
    }

    public String getNombreVisitante() {
        return nombreVisitante;
    }

    public void setNombreVisitante(String nombreVisitante) {
        this.nombreVisitante = nombreVisitante;
    }

    public int getCantidadVisitas() {
        return cantidadVisitas;
    }

    public void setCantidadVisitas(int cantidadVisitas) {
        this.cantidadVisitas = cantidadVisitas;
    }

    public List<String> getVisitas() {
        return visitas;
    }

    public void setVisitas(List<String> visitas) {
        this.visitas = visitas;
    }
}