package com.techparkuq.backend.datastructures;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ListaEnlazadaTest {

    @Test
    void debeAgregarElementosYConservarElOrden() {
        ListaEnlazada<String> lista = new ListaEnlazada<>();

        lista.agregar("Montaña Rusa");
        lista.agregar("Río Salvaje");
        lista.agregar("Carrusel");

        List<String> elementos = lista.toList();

        assertEquals(3, lista.size());
        assertEquals("Montaña Rusa", elementos.get(0));
        assertEquals("Río Salvaje", elementos.get(1));
        assertEquals("Carrusel", elementos.get(2));
    }

    @Test
    void debeIniciarVacia() {
        ListaEnlazada<String> lista = new ListaEnlazada<>();

        assertTrue(lista.estaVacia());
        assertEquals(0, lista.size());
    }
}
