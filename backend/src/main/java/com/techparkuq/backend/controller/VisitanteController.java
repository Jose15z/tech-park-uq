package com.techparkuq.backend.controller;

import com.techparkuq.backend.model.Visitante;
import com.techparkuq.backend.service.VisitanteService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/visitantes")
@CrossOrigin(origins = "*")
public class VisitanteController {

    private final VisitanteService visitanteService;

    public VisitanteController(VisitanteService visitanteService) {
        this.visitanteService = visitanteService;
    }

    @GetMapping
    public List<Visitante> listarVisitantes() {
        return visitanteService.listarVisitantes();
    }

    @GetMapping("/{id}")
    public Visitante obtenerVisitantePorId(@PathVariable Long id) {
        return visitanteService.obtenerVisitantePorId(id);
    }

    @GetMapping("/documento/{documento}")
    public Visitante obtenerVisitantePorDocumento(@PathVariable String documento) {
        return visitanteService.obtenerVisitantePorDocumento(documento);
    }

    @PostMapping
    public Visitante crearVisitante(@RequestBody Visitante visitante) {
        return visitanteService.guardarVisitante(visitante);
    }

    @PutMapping("/{id}")
    public Visitante actualizarVisitante(@PathVariable Long id, @RequestBody Visitante visitante) {
        return visitanteService.actualizarVisitante(id, visitante);
    }

    @DeleteMapping("/{id}")
    public void eliminarVisitante(@PathVariable Long id) {
        visitanteService.eliminarVisitante(id);
    }
}