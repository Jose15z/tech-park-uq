package com.techparkuq.backend.dto;

import java.util.List;

public class AlertaClimaticaResponseDTO {

    private String mensaje;
    private String tipoAlerta;
    private int cantidadAtraccionesCerradas;
    private List<String> atraccionesAfectadas;

    public AlertaClimaticaResponseDTO() {
    }

    public AlertaClimaticaResponseDTO(String mensaje,
                                      String tipoAlerta,
                                      int cantidadAtraccionesCerradas,
                                      List<String> atraccionesAfectadas) {
        this.mensaje = mensaje;
        this.tipoAlerta = tipoAlerta;
        this.cantidadAtraccionesCerradas = cantidadAtraccionesCerradas;
        this.atraccionesAfectadas = atraccionesAfectadas;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

    public String getTipoAlerta() {
        return tipoAlerta;
    }

    public void setTipoAlerta(String tipoAlerta) {
        this.tipoAlerta = tipoAlerta;
    }

    public int getCantidadAtraccionesCerradas() {
        return cantidadAtraccionesCerradas;
    }

    public void setCantidadAtraccionesCerradas(int cantidadAtraccionesCerradas) {
        this.cantidadAtraccionesCerradas = cantidadAtraccionesCerradas;
    }

    public List<String> getAtraccionesAfectadas() {
        return atraccionesAfectadas;
    }

    public void setAtraccionesAfectadas(List<String> atraccionesAfectadas) {
        this.atraccionesAfectadas = atraccionesAfectadas;
    }
}