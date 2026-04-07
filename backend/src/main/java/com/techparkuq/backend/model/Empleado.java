package com.techparkuq.backend.model;

import com.techparkuq.backend.enums.TipoEmpleado;
import jakarta.persistence.*;

@Entity
@Table(name = "empleados")
public class Empleado {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nombre;

    @Column(nullable = false, unique = true)
    private String documento;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private TipoEmpleado tipoEmpleado;

    @ManyToOne
    @JoinColumn(name = "zona_id")
    private Zona zonaAsignada;

    public Empleado() {
    }

    public Empleado(String nombre, String documento, TipoEmpleado tipoEmpleado, Zona zonaAsignada) {
        this.nombre = nombre;
        this.documento = documento;
        this.tipoEmpleado = tipoEmpleado;
        this.zonaAsignada = zonaAsignada;
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

    public String getDocumento() {
        return documento;
    }

    public void setDocumento(String documento) {
        this.documento = documento;
    }

    public TipoEmpleado getTipoEmpleado() {
        return tipoEmpleado;
    }

    public void setTipoEmpleado(TipoEmpleado tipoEmpleado) {
        this.tipoEmpleado = tipoEmpleado;
    }

    public Zona getZonaAsignada() {
        return zonaAsignada;
    }

    public void setZonaAsignada(Zona zonaAsignada) {
        this.zonaAsignada = zonaAsignada;
    }
}