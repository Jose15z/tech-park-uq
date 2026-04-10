package com.techparkuq.backend.service;

import com.techparkuq.backend.datastructures.ListaEnlazada;
import com.techparkuq.backend.dto.HistorialVisitasResponseDTO;
import com.techparkuq.backend.model.RegistroVisita;
import com.techparkuq.backend.model.Visitante;
import org.springframework.stereotype.Service;

import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class HistorialVisitasService {

    private final VisitanteService visitanteService;
    private final Map<Long, ListaEnlazada<RegistroVisita>> historialesPorVisitante = new HashMap<>();

    public HistorialVisitasService(VisitanteService visitanteService) {
        this.visitanteService = visitanteService;
    }

    public void registrarVisita(RegistroVisita registroVisita) {
        ListaEnlazada<RegistroVisita> historial = historialesPorVisitante.computeIfAbsent(
                registroVisita.getVisitanteId(),
                id -> new ListaEnlazada<>()
        );

        historial.agregar(registroVisita);
    }

    public HistorialVisitasResponseDTO obtenerHistorial(Long visitanteId) {
        Visitante visitante = visitanteService.obtenerVisitantePorId(visitanteId);

        ListaEnlazada<RegistroVisita> historial = historialesPorVisitante.computeIfAbsent(
                visitanteId,
                id -> new ListaEnlazada<>()
        );

        List<String> visitasFormateadas = new ArrayList<>();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

        for (RegistroVisita visita : historial.toList()) {
            visitasFormateadas.add(
                    visita.getNombreAtraccion() + " - " + visita.getFechaHora().format(formatter)
            );
        }

        return new HistorialVisitasResponseDTO(
                visitante.getId(),
                visitante.getNombre(),
                historial.size(),
                visitasFormateadas
        );
    }
}