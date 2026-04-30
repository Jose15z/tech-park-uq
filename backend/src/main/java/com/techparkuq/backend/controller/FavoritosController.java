package com.techparkuq.backend.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.techparkuq.backend.dto.FavoritoRequestDTO;
import com.techparkuq.backend.dto.FavoritosResponseDTO;
import com.techparkuq.backend.service.FavoritosService;

@RestController
@RequestMapping("/api/favoritos")
@CrossOrigin(origins = "*")
public class FavoritosController {

    private final FavoritosService favoritosService;

    public FavoritosController(FavoritosService favoritosService) {
        this.favoritosService = favoritosService;
    }

    @PostMapping("/agregar")
    public FavoritosResponseDTO agregarFavorito(@RequestBody FavoritoRequestDTO favoritoRequestDTO) {
        return favoritosService.agregarFavorito(
                favoritoRequestDTO.getVisitanteId(),
                favoritoRequestDTO.getAtraccionId()
        );
    }

    @DeleteMapping("/eliminar")
    public FavoritosResponseDTO eliminarFavorito(@RequestBody FavoritoRequestDTO favoritoRequestDTO) {
        return favoritosService.eliminarFavorito(
                favoritoRequestDTO.getVisitanteId(),
                favoritoRequestDTO.getAtraccionId()
        );
    }

    @GetMapping("/{visitanteId}")
    public FavoritosResponseDTO listarFavoritos(@PathVariable Long visitanteId) {
        return favoritosService.listarFavoritos(visitanteId);
    }
}