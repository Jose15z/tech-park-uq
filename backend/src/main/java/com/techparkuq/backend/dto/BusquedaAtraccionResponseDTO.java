package com.techparkuq.backend.dto;

public class BusquedaAtraccionResponseDTO {

    private Long id;
    private String nombre;
    private String tipo;
    private String estado;
    private String zona;
    private int tiempoEspera;

    public BusquedaAtraccionResponseDTO() {
    }

    public BusquedaAtraccionResponseDTO(Long id, String nombre, String tipo,
                                        String estado, String zona, int tiempoEspera) {
        this.id = id;
        this.nombre = nombre;
        this.tipo = tipo;
        this.estado = estado;
        this.zona = zona;
        this.tiempoEspera = tiempoEspera;
    }

    public Long getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public String getTipo() {
        return tipo;
    }

    public String getEstado() {
        return estado;
    }

    public String getZona() {
        return zona;
    }

    public int getTiempoEspera() {
        return tiempoEspera;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public void setZona(String zona) {
        this.zona = zona;
    }

    public void setTiempoEspera(int tiempoEspera) {
        this.tiempoEspera = tiempoEspera;
    }
}