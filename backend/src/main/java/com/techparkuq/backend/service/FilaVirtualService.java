package com.techparkuq.backend.service;

import com.techparkuq.backend.datastructures.ColaPrioridadVisitantes;
import com.techparkuq.backend.dto.FilaVirtualResponseDTO;
import com.techparkuq.backend.enums.TipoTicket;
import com.techparkuq.backend.exception.AtraccionNoDisponibleException;
import com.techparkuq.backend.model.Atraccion;
import com.techparkuq.backend.model.Ticket;
import com.techparkuq.backend.model.Visitante;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class FilaVirtualService {

    private final VisitanteService visitanteService;
    private final AtraccionService atraccionService;

    private final Map<Long, ColaPrioridadVisitantes<Visitante>> filasPorAtraccion = new HashMap<>();

    public FilaVirtualService(VisitanteService visitanteService, AtraccionService atraccionService) {
        this.visitanteService = visitanteService;
        this.atraccionService = atraccionService;
    }

    public FilaVirtualResponseDTO unirseAFila(Long visitanteId, Long atraccionId) {
        Visitante visitante = visitanteService.obtenerVisitantePorId(visitanteId);
        Atraccion atraccion = atraccionService.obtenerAtraccionPorId(atraccionId);

        if (visitante.getTicket() == null || !visitante.getTicket().isActivo()) {
            throw new AtraccionNoDisponibleException("El visitante no tiene un ticket activo");
        }

        ColaPrioridadVisitantes<Visitante> cola = filasPorAtraccion.computeIfAbsent(
                atraccionId,
                id -> new ColaPrioridadVisitantes<>()
        );

        int prioridad = obtenerPrioridad(visitante.getTicket());
        cola.encolar(visitante, prioridad);

        return construirRespuesta(
                "Visitante agregado a la fila virtual correctamente",
                atraccion,
                cola
        );
    }

    public FilaVirtualResponseDTO procesarSiguiente(Long atraccionId) {
        Atraccion atraccion = atraccionService.obtenerAtraccionPorId(atraccionId);

        ColaPrioridadVisitantes<Visitante> cola = filasPorAtraccion.get(atraccionId);

        if (cola == null || cola.estaVacia()) {
            throw new AtraccionNoDisponibleException("No hay visitantes en la fila para esta atracción");
        }

        Visitante atendido = cola.desencolar();

        return construirRespuesta(
                "Se procesó el siguiente visitante: " + atendido.getNombre(),
                atraccion,
                cola
        );
    }

    public FilaVirtualResponseDTO verFila(Long atraccionId) {
        Atraccion atraccion = atraccionService.obtenerAtraccionPorId(atraccionId);

        ColaPrioridadVisitantes<Visitante> cola = filasPorAtraccion.computeIfAbsent(
                atraccionId,
                id -> new ColaPrioridadVisitantes<>()
        );

        return construirRespuesta(
                "Estado actual de la fila virtual",
                atraccion,
                cola
        );
    }

    private int obtenerPrioridad(Ticket ticket) {
        if (ticket.getTipo() == TipoTicket.FAST_PASS) {
            return 1;
        }
        return 2;
    }

    private FilaVirtualResponseDTO construirRespuesta(String mensaje,
                                                      Atraccion atraccion,
                                                      ColaPrioridadVisitantes<Visitante> cola) {
        List<String> nombres = new ArrayList<>();

        for (Visitante visitante : cola.listarElementos()) {
            String etiqueta = visitante.getTicket().getTipo() == TipoTicket.FAST_PASS
                    ? visitante.getNombre() + " (FAST_PASS)"
                    : visitante.getNombre() + " (GENERAL)";
            nombres.add(etiqueta);
        }

        return new FilaVirtualResponseDTO(
                mensaje,
                atraccion.getNombre(),
                cola.size(),
                nombres
        );
    }
}
