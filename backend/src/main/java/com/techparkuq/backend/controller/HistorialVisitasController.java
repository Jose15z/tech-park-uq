package com.techparkuq.backend.controller;

import com.techparkuq.backend.dto.HistorialVisitasResponseDTO;
import com.techparkuq.backend.service.HistorialVisitasService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/historial")
@CrossOrigin(origins = "*")
public class HistorialVisitasController {

    private final HistorialVisitasService historialVisitasService;

    public HistorialVisitasController(HistorialVisitasService historialVisitasService) {
        this.historialVisitasService = historialVisitasService;
    }

    @GetMapping("/{visitanteId}")
    public HistorialVisitasResponseDTO obtenerHistorial(@PathVariable Long visitanteId) {
        return historialVisitasService.obtenerHistorial(visitanteId);
    }
}