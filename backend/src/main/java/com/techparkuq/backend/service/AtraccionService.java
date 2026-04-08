package com.techparkuq.backend.service;

import com.techparkuq.backend.exception.RecursoNoEncontradoException;
import com.techparkuq.backend.model.Atraccion;
import com.techparkuq.backend.model.Zona;
import com.techparkuq.backend.repository.AtraccionRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AtraccionService {

    private final AtraccionRepository atraccionRepository;
    private final ZonaService zonaService;

    public AtraccionService(AtraccionRepository atraccionRepository, ZonaService zonaService) {
        this.atraccionRepository = atraccionRepository;
        this.zonaService = zonaService;
    }

    public List<Atraccion> listarAtracciones() {
        return atraccionRepository.findAll();
    }

    public Atraccion obtenerAtraccionPorId(Long id) {
        return atraccionRepository.findById(id)
                .orElseThrow(() -> new RecursoNoEncontradoException("Atracción no encontrada con id: " + id));
    }

    public Atraccion guardarAtraccion(Atraccion atraccion) {
        if (atraccion.getZona() != null && atraccion.getZona().getId() != null) {
            Zona zona = zonaService.obtenerZonaPorId(atraccion.getZona().getId());
            atraccion.setZona(zona);
        } else {
            throw new RecursoNoEncontradoException("La atracción debe estar asociada a una zona válida.");
        }

        return atraccionRepository.save(atraccion);
    }

    public Atraccion actualizarAtraccion(Long id, Atraccion atraccionActualizada) {
        Atraccion atraccionExistente = obtenerAtraccionPorId(id);

        atraccionExistente.setNombre(atraccionActualizada.getNombre());
        atraccionExistente.setTipo(atraccionActualizada.getTipo());
        atraccionExistente.setCapacidadMaximaPorCiclo(atraccionActualizada.getCapacidadMaximaPorCiclo());
        atraccionExistente.setAlturaMinima(atraccionActualizada.getAlturaMinima());
        atraccionExistente.setEdadMinima(atraccionActualizada.getEdadMinima());
        atraccionExistente.setCostoAdicional(atraccionActualizada.getCostoAdicional());
        atraccionExistente.setVisitantesAcumulados(atraccionActualizada.getVisitantesAcumulados());
        atraccionExistente.setTiempoEspera(atraccionActualizada.getTiempoEspera());
        atraccionExistente.setEstado(atraccionActualizada.getEstado());
        atraccionExistente.setMotivoCierre(atraccionActualizada.getMotivoCierre());

        if (atraccionActualizada.getZona() != null && atraccionActualizada.getZona().getId() != null) {
            Zona zona = zonaService.obtenerZonaPorId(atraccionActualizada.getZona().getId());
            atraccionExistente.setZona(zona);
        }

        return atraccionRepository.save(atraccionExistente);
    }

    public void eliminarAtraccion(Long id) {
        Atraccion atraccion = obtenerAtraccionPorId(id);
        atraccionRepository.delete(atraccion);
    }
}