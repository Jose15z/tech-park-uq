package com.techparkuq.backend.dto;

public class RutaRequestDTO {

    private Long origenId;
    private Long destinoId;

    public RutaRequestDTO() {
    }

    public RutaRequestDTO(Long origenId, Long destinoId) {
        this.origenId = origenId;
        this.destinoId = destinoId;
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
}