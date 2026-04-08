package com.techparkuq.backend.service;

import com.techparkuq.backend.exception.RecursoNoEncontradoException;
import com.techparkuq.backend.model.Zona;
import com.techparkuq.backend.repository.ZonaRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ZonaService {

    private final ZonaRepository zonaRepository;

    public ZonaService(ZonaRepository zonaRepository) {
        this.zonaRepository = zonaRepository;
    }

    public List<Zona> listarZonas() {
        return zonaRepository.findAll();
    }

    public Zona obtenerZonaPorId(Long id) {
        return zonaRepository.findById(id)
                .orElseThrow(() -> new RecursoNoEncontradoException("Zona no encontrada con id: " + id));
    }

    public Zona guardarZona(Zona zona) {
        return zonaRepository.save(zona);
    }

    public Zona actualizarZona(Long id, Zona zonaActualizada) {
        Zona zonaExistente = obtenerZonaPorId(id);

        zonaExistente.setNombre(zonaActualizada.getNombre());
        zonaExistente.setCapacidadMaxima(zonaActualizada.getCapacidadMaxima());

        return zonaRepository.save(zonaExistente);
    }

    public void eliminarZona(Long id) {
        Zona zona = obtenerZonaPorId(id);
        zonaRepository.delete(zona);
    }
}