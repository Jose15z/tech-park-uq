package com.techparkuq.backend.dto;

import java.util.List;

public class FilaVirtualResponseDTO {

    private String mensaje;
    private String atraccion;
    private int cantidadEnFila;
    private List<String> visitantesEnFila;

    public FilaVirtualResponseDTO() {
    }

    public FilaVirtualResponseDTO(String mensaje, String atraccion, int cantidadEnFila, List<String> visitantesEnFila) {
        this.mensaje = mensaje;
        this.atraccion = atraccion;
        this.cantidadEnFila = cantidadEnFila;
        this.visitantesEnFila = visitantesEnFila;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

    public String getAtraccion() {
        return atraccion;
    }

    public void setAtraccion(String atraccion) {
        this.atraccion = atraccion;
    }

    public int getCantidadEnFila() {
        return cantidadEnFila;
    }

    public void setCantidadEnFila(int cantidadEnFila) {
        this.cantidadEnFila = cantidadEnFila;
    }

    public List<String> getVisitantesEnFila() {
        return visitantesEnFila;
    }

    public void setVisitantesEnFila(List<String> visitantesEnFila) {
        this.visitantesEnFila = visitantesEnFila;
    }
}