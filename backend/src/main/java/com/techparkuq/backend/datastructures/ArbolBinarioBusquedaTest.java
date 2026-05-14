package com.techparkuq.backend.datastructures;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ArbolBinarioBusquedaTest {

    @Test
    void debeBuscarElementoInsertado() {
        ArbolBinarioBusqueda<Integer> arbol = new ArbolBinarioBusqueda<>(Integer::compareTo);

        arbol.insertar(50);
        arbol.insertar(30);
        arbol.insertar(70);
        arbol.insertar(10);

        Integer encontrado = arbol.buscar(30);

        assertNotNull(encontrado);
        assertEquals(30, encontrado);
    }

    @Test
    void debeRetornarNullSiElementoNoExiste() {
        ArbolBinarioBusqueda<Integer> arbol = new ArbolBinarioBusqueda<>(Integer::compareTo);

        arbol.insertar(50);
        arbol.insertar(30);
        arbol.insertar(70);

        assertNull(arbol.buscar(99));
    }

    @Test
    void debeRecorrerEnOrden() {
        ArbolBinarioBusqueda<Integer> arbol = new ArbolBinarioBusqueda<>(Integer::compareTo);

        arbol.insertar(50);
        arbol.insertar(30);
        arbol.insertar(70);
        arbol.insertar(10);
        arbol.insertar(40);

        List<Integer> recorrido = arbol.recorrerInOrden();

        assertEquals(List.of(10, 30, 40, 50, 70), recorrido);
    }
}