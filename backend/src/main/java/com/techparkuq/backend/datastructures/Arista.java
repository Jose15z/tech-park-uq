package com.techparkuq.backend.datastructures;

public class Arista {

    private Long destinoId;
    private int peso;

    public Arista(Long destinoId, int peso) {
        this.destinoId = destinoId;
        this.peso = peso;
    }

    public Long getDestinoId() {
        return destinoId;
    }

    public void setDestinoId(Long destinoId) {
        this.destinoId = destinoId;
    }

    public int getPeso() {
        return peso;
    }

    public void setPeso(int peso) {
        this.peso = peso;
    }
}