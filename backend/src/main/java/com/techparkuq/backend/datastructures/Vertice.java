package com.techparkuq.backend.datastructures;

import java.util.ArrayList;
import java.util.List;

public class Vertice {

    private Long atraccionId;
    private List<Arista> adyacentes;

    public Vertice(Long atraccionId) {
        this.atraccionId = atraccionId;
        this.adyacentes = new ArrayList<>();
    }

    public Long getAtraccionId() {
        return atraccionId;
    }

    public void setAtraccionId(Long atraccionId) {
        this.atraccionId = atraccionId;
    }

    public List<Arista> getAdyacentes() {
        return adyacentes;
    }

    public void agregarArista(Arista arista) {
        this.adyacentes.add(arista);
    }
}