package com.techparkuq.backend.controller;

import com.techparkuq.backend.dto.ConexionAtraccionDTO;
import com.techparkuq.backend.dto.RutaRequestDTO;
import com.techparkuq.backend.dto.RutaResponseDTO;
import com.techparkuq.backend.service.RutaService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/rutas")
@CrossOrigin(origins = "*")
public class RutaController {

    private final RutaService rutaService;

    public RutaController(RutaService rutaService) {
        this.rutaService = rutaService;
    }

    @PostMapping("/conectar")
    public String conectarAtracciones(@RequestBody ConexionAtraccionDTO conexionDTO) {
        rutaService.conectarAtracciones(
                conexionDTO.getOrigenId(),
                conexionDTO.getDestinoId(),
                conexionDTO.getPeso()
        );
        return "Conexión creada correctamente entre atracciones";
    }

    @PostMapping("/optima")
    public RutaResponseDTO calcularRuta(@RequestBody RutaRequestDTO rutaRequestDTO) {
        return rutaService.calcularRutaOptima(
                rutaRequestDTO.getOrigenId(),
                rutaRequestDTO.getDestinoId()
        );
    }
}