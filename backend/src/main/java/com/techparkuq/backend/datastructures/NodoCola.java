package com.techparkuq.backend.datastructures;

public class NodoCola<T> {

    private T dato;
    private int prioridad;
    private NodoCola<T> siguiente;

    public NodoCola(T dato, int prioridad) {
        this.dato = dato;
        this.prioridad = prioridad;
        this.siguiente = null;
    }

    public T getDato() {
        return dato;
    }

    public void setDato(T dato) {
        this.dato = dato;
    }