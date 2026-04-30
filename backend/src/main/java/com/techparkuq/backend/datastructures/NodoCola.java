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

    public int getPrioridad() {
        return prioridad;
    }

    public void setPrioridad(int prioridad) {
        this.prioridad = prioridad;
    }

    public NodoCola<T> getSiguiente() {
        return siguiente;
    }

    public void setSiguiente(NodoCola<T> siguiente) {
        this.siguiente = siguiente;
    }

    public T getDato() {
        return dato;
    }

    public void setDato(T dato) {
        this.dato = dato;
    }}
