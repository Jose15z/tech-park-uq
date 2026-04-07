package com.techparkuq.backend.model;

import com.techparkuq.backend.enums.TipoTicket;
import jakarta.persistence.*;

@Entity
@Table(name = "tickets")
public class Ticket {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    private TipoTicket tipo;

    @Column(nullable = false)
    private boolean activo;

    public Ticket() {
    }

    public Ticket(TipoTicket tipo, boolean activo) {
        this.tipo = tipo;
        this.activo = activo;
    }

    public Long getId() {
        return id;
    }

    public TipoTicket getTipo() {
        return tipo;
    }

    public void setTipo(TipoTicket tipo) {
        this.tipo = tipo;
    }

    public boolean isActivo() {
        return activo;
    }

    public void setActivo(boolean activo) {
        this.activo = activo;
    }
}