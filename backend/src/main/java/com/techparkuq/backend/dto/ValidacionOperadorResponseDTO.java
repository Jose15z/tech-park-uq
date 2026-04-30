package com.techparkuq.backend.dto;

public class ValidacionOperadorResponseDTO {

    private String mensaje;
    private boolean autorizado;
    private String nombreOperador;
    private String nombreAtraccion;
    private String nombreZona;

    public ValidacionOperadorResponseDTO() {
    }

    public ValidacionOperadorResponseDTO(String mensaje, boolean autorizado,
                                         String nombreOperador, String nombreAtraccion,
                                         String nombreZona) {
        this.mensaje = mensaje;
        this.autorizado = autorizado;
        this.nombreOperador = nombreOperador;
        this.nombreAtraccion = nombreAtraccion;
        this.nombreZona = nombreZona;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

    public boolean isAutorizado() {
        return autorizado;
    }

    public void setAutorizado(boolean autorizado) {
        this.autorizado = autorizado;
    }

    public String getNombreOperador() {
        return nombreOperador;
    }

    public void setNombreOperador(String nombreOperador) {
        this.nombreOperador = nombreOperador;
    }

    public String getNombreAtraccion() {
        return nombreAtraccion;
    }

    public void setNombreAtraccion(String nombreAtraccion) {
        this.nombreAtraccion = nombreAtraccion;
    }

    public String getNombreZona() {
        return nombreZona;
    }

    public void setNombreZona(String nombreZona) {
        this.nombreZona = nombreZona;
    }
}