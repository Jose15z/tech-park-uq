package com.techparkuq.backend.controller;

import com.techparkuq.backend.model.Zona;
import com.techparkuq.backend.service.ZonaService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/zonas")
@CrossOrigin(origins = "*")
public class ZonaController {

    private final ZonaService zonaService;

    public ZonaController(ZonaService zonaService) {
        this.zonaService = zonaService;
    }

    @GetMapping
    public List<Zona> listarZonas() {
        return zonaService.listarZonas();
    }

    @GetMapping("/{id}")
    public Zona obtenerZonaPorId(@PathVariable Long id) {
        return zonaService.obtenerZonaPorId(id);
    }

    @PostMapping
    public Zona crearZona(@RequestBody Zona zona) {
        return zonaService.guardarZona(zona);
    }

    @PutMapping("/{id}")
    public Zona actualizarZona(@PathVariable Long id, @RequestBody Zona zona) {
        return zonaService.actualizarZona(id, zona);
    }

    @DeleteMapping("/{id}")
    public void eliminarZona(@PathVariable Long id) {
        zonaService.eliminarZona(id);
    }
}