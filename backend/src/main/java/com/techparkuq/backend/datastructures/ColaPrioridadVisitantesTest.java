package com.techparkuq.backend.datastructures;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ColaPrioridadVisitantesTest {

    @Test
    void debeProcesarPrimeroElElementoConMayorPrioridad() {
        ColaPrioridadVisitantes<String> cola = new ColaPrioridadVisitantes<>();

        cola.encolar("Visitante General", 2);
        cola.encolar("Visitante Fast Pass", 1);
        cola.encolar("Otro Visitante General", 2);

        assertEquals("Visitante Fast Pass", cola.desencolar());
        assertEquals("Visitante General", cola.desencolar());
        assertEquals("Otro Visitante General", cola.desencolar());
    }

    @Test
    void debeRetornarNullCuandoLaColaEstaVacia() {
        ColaPrioridadVisitantes<String> cola = new ColaPrioridadVisitantes<>();

        assertTrue(cola.estaVacia());
        assertNull(cola.desencolar());
    }
}

