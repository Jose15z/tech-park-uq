package com.techparkuq.backend.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.techparkuq.backend.datastructures.MiSet;
import com.techparkuq.backend.dto.FavoritosResponseDTO;
import com.techparkuq.backend.exception.RecursoNoEncontradoException;
import com.techparkuq.backend.model.Atraccion;
import com.techparkuq.backend.model.Visitante;

@Service
public class FavoritosService {

    private final VisitanteService visitanteService;
    private final AtraccionService atraccionService;

    private final Map<Long, MiSet<Long>> favoritosPorVisitante = new HashMap<>();

    public FavoritosService(VisitanteService visitanteService, AtraccionService atraccionService) {
        this.visitanteService = visitanteService;
        this.atraccionService = atraccionService;
    }

    public FavoritosResponseDTO agregarFavorito(Long visitanteId, Long atraccionId) {
        Visitante visitante = visitanteService.obtenerVisitantePorId(visitanteId);
        Atraccion atraccion = atraccionService.obtenerAtraccionPorId(atraccionId);

        MiSet<Long> favoritos = favoritosPorVisitante.computeIfAbsent(
                visitanteId,
                id -> new MiSet<>()
        );

        favoritos.agregar(atraccion.getId());

        return construirRespuesta(visitante, favoritos);
    }

    public FavoritosResponseDTO eliminarFavorito(Long visitanteId, Long atraccionId) {
        Visitante visitante = visitanteService.obtenerVisitantePorId(visitanteId);
        atraccionService.obtenerAtraccionPorId(atraccionId);

        MiSet<Long> favoritos = favoritosPorVisitante.computeIfAbsent(
                visitanteId,
                id -> new MiSet<>()
        );

        boolean eliminado = favoritos.eliminar(atraccionId);

        if (!eliminado) {
            throw new RecursoNoEncontradoException("La atracción no estaba en favoritos");
        }

        return construirRespuesta(visitante, favoritos);
    }

    public FavoritosResponseDTO listarFavoritos(Long visitanteId) {
        Visitante visitante = visitanteService.obtenerVisitantePorId(visitanteId);

        MiSet<Long> favoritos = favoritosPorVisitante.computeIfAbsent(
                visitanteId,
                id -> new MiSet<>()
        );

        return construirRespuesta(visitante, favoritos);
    }

    private FavoritosResponseDTO construirRespuesta(Visitante visitante, MiSet<Long> favoritos) {
        List<String> nombresAtracciones = new ArrayList<>();

        for (Long atraccionId : favoritos.toList()) {
            Atraccion atraccion = atraccionService.obtenerAtraccionPorId(atraccionId);
            nombresAtracciones.add(atraccion.getNombre());
        }

        return new FavoritosResponseDTO(
                visitante.getId(),
                visitante.getNombre(),
                favoritos.size(),
                nombresAtracciones
        );
    }
}