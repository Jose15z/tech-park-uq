package com.techparkuq.backend.model;

import com.techparkuq.backend.enums.EstadoAtraccion;
import com.techparkuq.backend.enums.TipoAtraccion;
import jakarta.persistence.*;
import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
@Table(name = "atracciones")
public class Atraccion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nombre;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private TipoAtraccion tipo;

    @Column(nullable = false)
    private int capacidadMaximaPorCiclo;

    @Column(nullable = false)
    private double alturaMinima;

    @Column(nullable = false)
    private int edadMinima;

    @Column(nullable = false)
    private double costoAdicional;

    @Column(nullable = false)
    private int visitantesAcumulados;

    @Column(nullable = false)
    private int tiempoEspera;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private EstadoAtraccion estado;

    private String motivoCierre;

    @ManyToOne
    @JsonBackReference
    @JoinColumn(name = "zona_id", nullable = false)
    private Zona zona;

    public Atraccion() {
    }

    public Atraccion(String nombre, TipoAtraccion tipo, int capacidadMaximaPorCiclo,
                     double alturaMinima, int edadMinima, double costoAdicional,
                     int visitantesAcumulados, int tiempoEspera,
                     EstadoAtraccion estado, String motivoCierre, Zona zona) {
        this.nombre = nombre;
        this.tipo = tipo;
        this.capacidadMaximaPorCiclo = capacidadMaximaPorCiclo;
        this.alturaMinima = alturaMinima;
        this.edadMinima = edadMinima;
        this.costoAdicional = costoAdicional;
        this.visitantesAcumulados = visitantesAcumulados;
        this.tiempoEspera = tiempoEspera;
        this.estado = estado;
        this.motivoCierre = motivoCierre;
        this.zona = zona;
    }

    public Long getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public TipoAtraccion getTipo() {
        return tipo;
    }

    public void setTipo(TipoAtraccion tipo) {
        this.tipo = tipo;
    }

    public int getCapacidadMaximaPorCiclo() {
        return capacidadMaximaPorCiclo;
    }

    public void setCapacidadMaximaPorCiclo(int capacidadMaximaPorCiclo) {
        this.capacidadMaximaPorCiclo = capacidadMaximaPorCiclo;
    }

    public double getAlturaMinima() {
        return alturaMinima;
    }

    public void setAlturaMinima(double alturaMinima) {
        this.alturaMinima = alturaMinima;
    }

    public int getEdadMinima() {
        return edadMinima;
    }

    public void setEdadMinima(int edadMinima) {
        this.edadMinima = edadMinima;
    }

    public double getCostoAdicional() {
        return costoAdicional;
    }

    public void setCostoAdicional(double costoAdicional) {
        this.costoAdicional = costoAdicional;
    }

    public int getVisitantesAcumulados() {
        return visitantesAcumulados;
    }

    public void setVisitantesAcumulados(int visitantesAcumulados) {
        this.visitantesAcumulados = visitantesAcumulados;
    }

    public int getTiempoEspera() {
        return tiempoEspera;
    }

    public void setTiempoEspera(int tiempoEspera) {
        this.tiempoEspera = tiempoEspera;
    }

    public EstadoAtraccion getEstado() {
        return estado;
    }

    public void setEstado(EstadoAtraccion estado) {
        this.estado = estado;
    }

    public String getMotivoCierre() {
        return motivoCierre;
    }

    public void setMotivoCierre(String motivoCierre) {
        this.motivoCierre = motivoCierre;
    }

    public Zona getZona() {
        return zona;
    }

    public void setZona(Zona zona) {
        this.zona = zona;
    }
}
