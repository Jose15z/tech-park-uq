package com.techparkuq.backend.datastructures;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MiSetTest {

    @Test
    void noDebePermitirElementosDuplicados() {
        MiSet<Long> favoritos = new MiSet<>();

        boolean primeraInsercion = favoritos.agregar(1L);
        boolean segundaInsercion = favoritos.agregar(1L);

        assertTrue(primeraInsercion);
        assertFalse(segundaInsercion);
        assertEquals(1, favoritos.size());
    }

    @Test
    void debeEliminarElementoExistente() {
        MiSet<Long> favoritos = new MiSet<>();

        favoritos.agregar(1L);
        favoritos.agregar(2L);

        boolean eliminado = favoritos.eliminar(1L);

        assertTrue(eliminado);
        assertFalse(favoritos.contiene(1L));
        assertTrue(favoritos.contiene(2L));
        assertEquals(1, favoritos.size());
    }
}