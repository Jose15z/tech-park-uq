package com.techparkuq.backend.controller;

import com.techparkuq.backend.dto.FilaVirtualIngresoDTO;
import com.techparkuq.backend.dto.FilaVirtualResponseDTO;
import com.techparkuq.backend.service.FilaVirtualService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/filas")
@CrossOrigin(origins = "*")
public class FilaVirtualController {

    private final FilaVirtualService filaVirtualService;

    public FilaVirtualController(FilaVirtualService filaVirtualService) {
        this.filaVirtualService = filaVirtualService;
    }

    @PostMapping("/unirse")
    public FilaVirtualResponseDTO unirseAFila(@RequestBody FilaVirtualIngresoDTO ingresoDTO) {
        return filaVirtualService.unirseAFila(
                ingresoDTO.getVisitanteId(),
                ingresoDTO.getAtraccionId()
        );
    }

    @PostMapping("/procesar/{atraccionId}")
    public FilaVirtualResponseDTO procesarSiguiente(@PathVariable Long atraccionId) {
        return filaVirtualService.procesarSiguiente(atraccionId);
    }

    @GetMapping("/{atraccionId}")
    public FilaVirtualResponseDTO verFila(@PathVariable Long atraccionId) {
        return filaVirtualService.verFila(atraccionId);
    }
}