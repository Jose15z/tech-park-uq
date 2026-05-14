package com.techparkuq.backend.datastructures;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class ArbolBinarioBusqueda<T> {

    private NodoABB<T> raiz;
    private final Comparator<T> comparador;

    public ArbolBinarioBusqueda(Comparator<T> comparador) {
        this.raiz = null;
        this.comparador = comparador;
    }

    public boolean estaVacio() {
        return raiz == null;
    }

    public void insertar(T dato) {
        raiz = insertarRecursivo(raiz, dato);
    }

    private NodoABB<T> insertarRecursivo(NodoABB<T> nodoActual, T dato) {
        if (nodoActual == null) {
            return new NodoABB<>(dato);
        }

        int comparacion = comparador.compare(dato, nodoActual.getDato());

        if (comparacion < 0) {
            nodoActual.setIzquierdo(insertarRecursivo(nodoActual.getIzquierdo(), dato));
        } else if (comparacion > 0) {
            nodoActual.setDerecho(insertarRecursivo(nodoActual.getDerecho(), dato));
        } else {
            nodoActual.setDato(dato);
        }

        return nodoActual;
    }

    public T buscar(T dato) {
        return buscarRecursivo(raiz, dato);
    }

    private T buscarRecursivo(NodoABB<T> nodoActual, T dato) {
        if (nodoActual == null) {
            return null;
        }

        int comparacion = comparador.compare(dato, nodoActual.getDato());

        if (comparacion == 0) {
            return nodoActual.getDato();
        }

        if (comparacion < 0) {
            return buscarRecursivo(nodoActual.getIzquierdo(), dato);
        }

        return buscarRecursivo(nodoActual.getDerecho(), dato);
    }

    public List<T> recorrerInOrden() {
        List<T> elementos = new ArrayList<>();
        recorrerInOrdenRecursivo(raiz, elementos);
        return elementos;
    }

    private void recorrerInOrdenRecursivo(NodoABB<T> nodoActual, List<T> elementos) {
        if (nodoActual == null) {
            return;
        }

        recorrerInOrdenRecursivo(nodoActual.getIzquierdo(), elementos);
        elementos.add(nodoActual.getDato());
        recorrerInOrdenRecursivo(nodoActual.getDerecho(), elementos);
    }

    public void limpiar() {
        raiz = null;
    }
}