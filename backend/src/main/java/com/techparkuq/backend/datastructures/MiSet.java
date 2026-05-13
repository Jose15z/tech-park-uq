package com.techparkuq.backend.datastructures;

import java.util.ArrayList;
import java.util.List;

public class MiSet<T> {

    private NodoSet<T> cabeza;
    private int size;

    public MiSet() {
        this.cabeza = null;
        this.size = 0;
    }

    public boolean agregar(T dato) {
        if (contiene(dato)) {
            return false;
        }

        NodoSet<T> nuevo = new NodoSet<>(dato);

        if (cabeza == null) {
            cabeza = nuevo;
        } else {
            NodoSet<T> actual = cabeza;

            while (actual.getSiguiente() != null) {
                actual = actual.getSiguiente();
            }

            actual.setSiguiente(nuevo);
        }

        size++;
        return true;
    }

    public boolean eliminar(T dato) {
        if (cabeza == null) {
            return false;
        }

        if (cabeza.getDato().equals(dato)) {
            cabeza = cabeza.getSiguiente();
            size--;
            return true;
        }

        NodoSet<T> actual = cabeza;

        while (actual.getSiguiente() != null) {
            if (actual.getSiguiente().getDato().equals(dato)) {
                actual.setSiguiente(actual.getSiguiente().getSiguiente());
                size--;
                return true;
            }

            actual = actual.getSiguiente();
        }

        return false;
    }

    public boolean contiene(T dato) {
        NodoSet<T> actual = cabeza;

        while (actual != null) {
            if (actual.getDato().equals(dato)) {
                return true;
            }

            actual = actual.getSiguiente();
        }

        return false;
    }

    public int size() {
        return size;
    }

    public List<T> toList() {
        List<T> elementos = new ArrayList<>();
        NodoSet<T> actual = cabeza;

        while (actual != null) {
            elementos.add(actual.getDato());
            actual = actual.getSiguiente();
        }

        return elementos;
    }
}