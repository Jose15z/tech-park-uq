package com.techparkuq.backend.dto;

public class ConexionAtraccionDTO {

    private Long origenId;
    private Long destinoId;
    private int peso;

    public ConexionAtraccionDTO() {
    }

    public ConexionAtraccionDTO(Long origenId, Long destinoId, int peso) {
        this.origenId = origenId;
        this.destinoId = destinoId;
        this.peso = peso;
    }

    public Long getOrigenId() {
        return origenId;
    }

    public void setOrigenId(Long origenId) {
        this.origenId = origenId;
    }

    public Long getDestinoId() {
        return destinoId;
    }

    public void setDestinoId(Long destinoId) {
        this.destinoId = destinoId;
    }

    public int getPeso() {
        return peso;
    }

    public void setPeso(int peso) {
        this.peso = peso;
    }
}