package com.techparkuq.backend.dto;

import java.util.List;

public class DatosDemoResponseDTO {

    private String mensaje;
    private int zonasCreadas;
    private int atraccionesCreadas;
    private int ticketsCreados;
    private int visitantesCreados;
    private int empleadosCreados;
    private List<String> resumen;

    public DatosDemoResponseDTO() {
    }

    public DatosDemoResponseDTO(String mensaje,
                                int zonasCreadas,
                                int atraccionesCreadas,
                                int ticketsCreados,
                                int visitantesCreados,
                                int empleadosCreados,
                                List<String> resumen) {
        this.mensaje = mensaje;
        this.zonasCreadas = zonasCreadas;
        this.atraccionesCreadas = atraccionesCreadas;
        this.ticketsCreados = ticketsCreados;
        this.visitantesCreados = visitantesCreados;
        this.empleadosCreados = empleadosCreados;
        this.resumen = resumen;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

    public int getZonasCreadas() {
        return zonasCreadas;
    }

    public void setZonasCreadas(int zonasCreadas) {
        this.zonasCreadas = zonasCreadas;
    }

    public int getAtraccionesCreadas() {
        return atraccionesCreadas;
    }

    public void setAtraccionesCreadas(int atraccionesCreadas) {
        this.atraccionesCreadas = atraccionesCreadas;
    }

    public int getTicketsCreados() {
        return ticketsCreados;
    }

    public void setTicketsCreados(int ticketsCreados) {
        this.ticketsCreados = ticketsCreados;
    }

    public int getVisitantesCreados() {
        return visitantesCreados;
    }

    public void setVisitantesCreados(int visitantesCreados) {
        this.visitantesCreados = visitantesCreados;
    }

    public int getEmpleadosCreados() {
        return empleadosCreados;
    }

    public void setEmpleadosCreados(int empleadosCreados) {
        this.empleadosCreados = empleadosCreados;
    }

    public List<String> getResumen() {
        return resumen;
    }

    public void setResumen(List<String> resumen) {
        this.resumen = resumen;
    }
}