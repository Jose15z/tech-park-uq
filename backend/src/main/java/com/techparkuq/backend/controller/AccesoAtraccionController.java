package com.techparkuq.backend.controller;

import com.techparkuq.backend.dto.AccesoAtraccionResponseDTO;
import com.techparkuq.backend.dto.IngresoAtraccionDTO;
import com.techparkuq.backend.service.AccesoAtraccionService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/accesos")
@CrossOrigin(origins = "*")
public class AccesoAtraccionController {

    private final AccesoAtraccionService accesoAtraccionService;

    public AccesoAtraccionController(AccesoAtraccionService accesoAtraccionService) {
        this.accesoAtraccionService = accesoAtraccionService;
    }

    @PostMapping("/ingresar")
    public AccesoAtraccionResponseDTO ingresarAtraccion(@RequestBody IngresoAtraccionDTO ingresoDTO) {
        return accesoAtraccionService.procesarIngreso(
                ingresoDTO.getVisitanteId(),
                ingresoDTO.getAtraccionId()
        );
    }
}