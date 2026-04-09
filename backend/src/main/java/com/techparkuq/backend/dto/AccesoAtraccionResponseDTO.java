package com.techparkuq.backend.dto;

public class AccesoAtraccionResponseDTO {

    private String mensaje;
    private String nombreVisitante;
    private String nombreAtraccion;
    private double saldoRestante;
    private int visitantesAcumulados;
    private String estadoAtraccion;

    public AccesoAtraccionResponseDTO() {
    }

    public AccesoAtraccionResponseDTO(String mensaje, String nombreVisitante, String nombreAtraccion,
                                      double saldoRestante, int visitantesAcumulados, String estadoAtraccion) {
        this.mensaje = mensaje;
        this.nombreVisitante = nombreVisitante;
        this.nombreAtraccion = nombreAtraccion;
        this.saldoRestante = saldoRestante;
        this.visitantesAcumulados = visitantesAcumulados;
        this.estadoAtraccion = estadoAtraccion;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

    public String getNombreVisitante() {
        return nombreVisitante;
    }

    public void setNombreVisitante(String nombreVisitante) {
        this.nombreVisitante = nombreVisitante;
    }

    public String getNombreAtraccion() {
        return nombreAtraccion;
    }

    public void setNombreAtraccion(String nombreAtraccion) {
        this.nombreAtraccion = nombreAtraccion;
    }

    public double getSaldoRestante() {
        return saldoRestante;
    }

    public void setSaldoRestante(double saldoRestante) {
        this.saldoRestante = saldoRestante;
    }

    public int getVisitantesAcumulados() {
        return visitantesAcumulados;
    }

    public void setVisitantesAcumulados(int visitantesAcumulados) {
        this.visitantesAcumulados = visitantesAcumulados;
    }

    public String getEstadoAtraccion() {
        return estadoAtraccion;
    }

    public void setEstadoAtraccion(String estadoAtraccion) {
        this.estadoAtraccion = estadoAtraccion;
    }
}