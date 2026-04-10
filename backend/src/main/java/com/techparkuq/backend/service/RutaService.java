package com.techparkuq.backend.service;

import com.techparkuq.backend.datastructures.GrafoParque;
import com.techparkuq.backend.dto.RutaResponseDTO;
import com.techparkuq.backend.exception.RecursoNoEncontradoException;
import com.techparkuq.backend.model.Atraccion;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class RutaService {

    private final AtraccionService atraccionService;
    private final GrafoParque grafoParque;

    public RutaService(AtraccionService atraccionService) {
        this.atraccionService = atraccionService;
        this.grafoParque = new GrafoParque();
    }

    @PostConstruct
    public void inicializarGrafoDemo() {
        // Este método se deja vacío si aún no se tiene atracciones cargadas.
    }

    public void conectarAtracciones(Long origenId, Long destinoId, int peso) {
        atraccionService.obtenerAtraccionPorId(origenId);
        atraccionService.obtenerAtraccionPorId(destinoId);
        grafoParque.agregarArista(origenId, destinoId, peso);
    }

    public RutaResponseDTO calcularRutaOptima(Long origenId, Long destinoId) {
        Atraccion origen = atraccionService.obtenerAtraccionPorId(origenId);
        Atraccion destino = atraccionService.obtenerAtraccionPorId(destinoId);

        List<Long> rutaIds = grafoParque.dijkstra(origenId, destinoId);

        if (rutaIds.isEmpty()) {
            throw new RecursoNoEncontradoException("No existe una ruta entre las atracciones seleccionadas");
        }

        List<String> nombresRuta = new ArrayList<>();
        for (Long id : rutaIds) {
            Atraccion atraccion = atraccionService.obtenerAtraccionPorId(id);
            nombresRuta.add(atraccion.getNombre());
        }

        int distanciaTotal = grafoParque.calcularDistanciaRuta(rutaIds);

        return new RutaResponseDTO(
                "Ruta óptima calculada correctamente desde " + origen.getNombre() + " hasta " + destino.getNombre(),
                nombresRuta,
                distanciaTotal
        );
    }
}