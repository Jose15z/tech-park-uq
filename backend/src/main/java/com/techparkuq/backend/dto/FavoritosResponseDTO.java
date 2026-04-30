package com.techparkuq.backend.dto;

import java.util.List;

public class FavoritosResponseDTO {

    private Long visitanteId;
    private String nombreVisitante;
    private int cantidadFavoritos;
    private List<String> atraccionesFavoritas;

    public FavoritosResponseDTO() {
    }

    public FavoritosResponseDTO(Long visitanteId, String nombreVisitante,
                                int cantidadFavoritos, List<String> atraccionesFavoritas) {
        this.visitanteId = visitanteId;
        this.nombreVisitante = nombreVisitante;
        this.cantidadFavoritos = cantidadFavoritos;
        this.atraccionesFavoritas = atraccionesFavoritas;
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

    public int getCantidadFavoritos() {
        return cantidadFavoritos;
    }

    public void setCantidadFavoritos(int cantidadFavoritos) {
        this.cantidadFavoritos = cantidadFavoritos;
    }

    public List<String> getAtraccionesFavoritas() {
        return atraccionesFavoritas;
    }

    public void setAtraccionesFavoritas(List<String> atraccionesFavoritas) {
        this.atraccionesFavoritas = atraccionesFavoritas;
    }
}