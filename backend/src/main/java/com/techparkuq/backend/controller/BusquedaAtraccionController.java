package com.techparkuq.backend.controller;

import com.techparkuq.backend.dto.BusquedaAtraccionResponseDTO;
import com.techparkuq.backend.service.BusquedaAtraccionService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/busqueda/atracciones")
@CrossOrigin(origins = "*")
public class BusquedaAtraccionController {

    private final BusquedaAtraccionService busquedaAtraccionService;

    public BusquedaAtraccionController(BusquedaAtraccionService busquedaAtraccionService) {
        this.busquedaAtraccionService = busquedaAtraccionService;
    }

    @GetMapping("/id/{id}")
    public BusquedaAtraccionResponseDTO buscarPorId(@PathVariable Long id) {
        return busquedaAtraccionService.buscarPorId(id);
    }

    @GetMapping("/nombre/{nombre}")
    public BusquedaAtraccionResponseDTO buscarPorNombre(@PathVariable String nombre) {
        return busquedaAtraccionService.buscarPorNombre(nombre);
    }
}