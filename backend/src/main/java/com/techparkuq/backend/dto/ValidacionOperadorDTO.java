package com.techparkuq.backend.dto;

public class ValidacionOperadorDTO {

    private Long operadorId;
    private Long atraccionId;

    public ValidacionOperadorDTO() {
    }

    public ValidacionOperadorDTO(Long operadorId, Long atraccionId) {
        this.operadorId = operadorId;
        this.atraccionId = atraccionId;
    }

    public Long getOperadorId() {
        return operadorId;
    }

    public void setOperadorId(Long operadorId) {
        this.operadorId = operadorId;
    }

    public Long getAtraccionId() {
        return atraccionId;
    }

    public void setAtraccionId(Long atraccionId) {
        this.atraccionId = atraccionId;
    }
}