package com.techparkuq.backend.datastructures;

import java.util.ArrayList;
import java.util.List;

public class ColaPrioridadVisitantes<T> {

    private NodoCola<T> frente;

    public ColaPrioridadVisitantes() {
        this.frente = null;
    }

    public boolean estaVacia() {
        return frente == null;
    }

    public void encolar(T dato, int prioridad) {
        NodoCola<T> nuevo = new NodoCola<>(dato, prioridad);

        if (frente == null || prioridad < frente.getPrioridad()) {
            nuevo.setSiguiente(frente);
            frente = nuevo;
            return;
        }

        NodoCola<T> actual = frente;

        while (actual.getSiguiente() != null &&
                actual.getSiguiente().getPrioridad() <= prioridad) {
            actual = actual.getSiguiente();
        }

        nuevo.setSiguiente(actual.getSiguiente());
        actual.setSiguiente(nuevo);
    }

    public T desencolar() {
        if (estaVacia()) {
            return null;
        }

        T dato = frente.getDato();
        frente = frente.getSiguiente();
        return dato;
    }

    public T verFrente() {
        if (estaVacia()) {
            return null;
        }
        return frente.getDato();
    }

    public int size() {
        int contador = 0;
        NodoCola<T> actual = frente;

        while (actual != null) {
            contador++;
            actual = actual.getSiguiente();
        }

        return contador;
    }

    public List<T> listarElementos() {
        List<T> elementos = new ArrayList<>();
        NodoCola<T> actual = frente;

        while (actual != null) {
            elementos.add(actual.getDato());
            actual = actual.getSiguiente();
        }

        return elementos;
    }
}