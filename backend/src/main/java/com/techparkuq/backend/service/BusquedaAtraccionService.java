package com.techparkuq.backend.service;

import com.techparkuq.backend.datastructures.ArbolBinarioBusqueda;
import com.techparkuq.backend.dto.BusquedaAtraccionResponseDTO;
import com.techparkuq.backend.exception.RecursoNoEncontradoException;
import com.techparkuq.backend.model.Atraccion;
import com.techparkuq.backend.repository.AtraccionRepository;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;

@Service
public class BusquedaAtraccionService {

    private final AtraccionRepository atraccionRepository;

    public BusquedaAtraccionService(AtraccionRepository atraccionRepository) {
        this.atraccionRepository = atraccionRepository;
    }

    public BusquedaAtraccionResponseDTO buscarPorId(Long id) {
        ArbolBinarioBusqueda<Atraccion> arbolPorId = construirArbolPorId();

        Atraccion atraccionBusqueda = new Atraccion();
        atraccionBusqueda.setId(id);

        Atraccion encontrada = arbolPorId.buscar(atraccionBusqueda);

        if (encontrada == null) {
            throw new RecursoNoEncontradoException("No se encontró una atracción con id: " + id);
        }

        return convertirADTO(encontrada);
    }

    public BusquedaAtraccionResponseDTO buscarPorNombre(String nombre) {
        ArbolBinarioBusqueda<Atraccion> arbolPorNombre = construirArbolPorNombre();

        Atraccion atraccionBusqueda = new Atraccion();
        atraccionBusqueda.setNombre(nombre);

        Atraccion encontrada = arbolPorNombre.buscar(atraccionBusqueda);

        if (encontrada == null) {
            throw new RecursoNoEncontradoException("No se encontró una atracción con nombre: " + nombre);
        }

        return convertirADTO(encontrada);
    }

    private ArbolBinarioBusqueda<Atraccion> construirArbolPorId() {
        ArbolBinarioBusqueda<Atraccion> arbol = new ArbolBinarioBusqueda<>(
                Comparator.comparing(Atraccion::getId)
        );

        List<Atraccion> atracciones = atraccionRepository.findAll();

        for (Atraccion atraccion : atracciones) {
            arbol.insertar(atraccion);
        }

        return arbol;
    }

    private ArbolBinarioBusqueda<Atraccion> construirArbolPorNombre() {
        ArbolBinarioBusqueda<Atraccion> arbol = new ArbolBinarioBusqueda<>(
                Comparator.comparing(
                        atraccion -> normalizarTexto(atraccion.getNombre())
                )
        );

        List<Atraccion> atracciones = atraccionRepository.findAll();

        for (Atraccion atraccion : atracciones) {
            arbol.insertar(atraccion);
        }

        return arbol;
    }

    private String normalizarTexto(String texto) {
        if (texto == null) {
            return "";
        }

        return texto.trim().toLowerCase();
    }

    private BusquedaAtraccionResponseDTO convertirADTO(Atraccion atraccion) {
        String nombreZona = atraccion.getZona() != null
                ? atraccion.getZona().getNombre()
                : "Sin zona asignada";

        return new BusquedaAtraccionResponseDTO(
                atraccion.getId(),
                atraccion.getNombre(),
                atraccion.getTipo().name(),
                atraccion.getEstado().name(),
                nombreZona,
                atraccion.getTiempoEspera()
        );
    }
}
