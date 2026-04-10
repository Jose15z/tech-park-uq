package com.techparkuq.backend.dto;

import java.util.List;

public class RutaResponseDTO {

    private String mensaje;
    private List<String> ruta;
    private int distanciaTotal;

    public RutaResponseDTO() {
    }

    public RutaResponseDTO(String mensaje, List<String> ruta, int distanciaTotal) {
        this.mensaje = mensaje;
        this.ruta = ruta;
        this.distanciaTotal = distanciaTotal;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

    public List<String> getRuta() {
        return ruta;
    }

    public void setRuta(List<String> ruta) {
        this.ruta = ruta;
    }

    public int getDistanciaTotal() {
        return distanciaTotal;
    }

    public void setDistanciaTotal(int distanciaTotal) {
        this.distanciaTotal = distanciaTotal;
    }
}