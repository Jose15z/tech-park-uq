package com.techparkuq.backend.controller;

import com.techparkuq.backend.model.Atraccion;
import com.techparkuq.backend.service.AtraccionService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/atracciones")
@CrossOrigin(origins = "*")
public class AtraccionController {

    private final AtraccionService atraccionService;

    public AtraccionController(AtraccionService atraccionService) {
        this.atraccionService = atraccionService;
    }

    @GetMapping
    public List<Atraccion> listarAtracciones() {
        return atraccionService.listarAtracciones();
    }

    @GetMapping("/{id}")
    public Atraccion obtenerAtraccionPorId(@PathVariable Long id) {
        return atraccionService.obtenerAtraccionPorId(id);
    }

    @PostMapping
    public Atraccion crearAtraccion(@RequestBody Atraccion atraccion) {
        return atraccionService.guardarAtraccion(atraccion);
    }

    @PutMapping("/{id}")
    public Atraccion actualizarAtraccion(@PathVariable Long id, @RequestBody Atraccion atraccion) {
        return atraccionService.actualizarAtraccion(id, atraccion);
    }

    @DeleteMapping("/{id}")
    public void eliminarAtraccion(@PathVariable Long id) {
        atraccionService.eliminarAtraccion(id);
    }
}