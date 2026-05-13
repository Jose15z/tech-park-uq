package com.techparkuq.backend.datastructures;

public class NodoSet<T> {

    private T dato;
    private NodoSet<T> siguiente;

    public NodoSet(T dato) {
        this.dato = dato;
        this.siguiente = null;
    }

    public T getDato() {
        return dato;
    }

    public void setDato(T dato) {
        this.dato = dato;
    }

    public NodoSet<T> getSiguiente() {
        return siguiente;
    }

    public void setSiguiente(NodoSet<T> siguiente) {
        this.siguiente = siguiente;
    }
}