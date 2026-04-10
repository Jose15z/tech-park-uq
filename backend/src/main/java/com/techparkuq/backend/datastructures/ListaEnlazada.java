package com.techparkuq.backend.datastructures;

import java.util.ArrayList;
import java.util.List;

public class ListaEnlazada<T> {

    private NodoLista<T> cabeza;
    private int size;

    public ListaEnlazada() {
        this.cabeza = null;
        this.size = 0;
    }

    public boolean estaVacia() {
        return cabeza == null;
    }

    public void agregar(T dato) {
        NodoLista<T> nuevo = new NodoLista<>(dato);

        if (cabeza == null) {
            cabeza = nuevo;
        } else {
            NodoLista<T> actual = cabeza;
            while (actual.getSiguiente() != null) {
                actual = actual.getSiguiente();
            }
            actual.setSiguiente(nuevo);
        }

        size++;
    }

    public int size() {
        return size;
    }

    public List<T> toList() {
        List<T> elementos = new ArrayList<>();
        NodoLista<T> actual = cabeza;

        while (actual != null) {
            elementos.add(actual.getDato());
            actual = actual.getSiguiente();
        }

        return elementos;
    }
}