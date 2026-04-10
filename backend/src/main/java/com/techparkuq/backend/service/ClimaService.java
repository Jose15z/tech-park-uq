package com.techparkuq.backend.service;

import com.techparkuq.backend.dto.AlertaClimaticaDTO;
import com.techparkuq.backend.dto.AlertaClimaticaResponseDTO;
import com.techparkuq.backend.enums.EstadoAtraccion;
import com.techparkuq.backend.enums.TipoAlertaClimatica;
import com.techparkuq.backend.enums.TipoAtraccion;
import com.techparkuq.backend.model.AlertaClimatica;
import com.techparkuq.backend.model.Atraccion;
import com.techparkuq.backend.repository.AlertaClimaticaRepository;
import com.techparkuq.backend.repository.AtraccionRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class ClimaService {

    private final AtraccionRepository atraccionRepository;
    private final AlertaClimaticaRepository alertaClimaticaRepository;

    public ClimaService(AtraccionRepository atraccionRepository,
                        AlertaClimaticaRepository alertaClimaticaRepository) {
        this.atraccionRepository = atraccionRepository;
        this.alertaClimaticaRepository = alertaClimaticaRepository;
    }

    @Transactional
    public AlertaClimaticaResponseDTO procesarAlerta(AlertaClimaticaDTO alertaDTO) {
        AlertaClimatica alerta = new AlertaClimatica(
                alertaDTO.getTipoAlerta(),
                alertaDTO.isActiva()
        );
        alertaClimaticaRepository.save(alerta);

        List<String> atraccionesAfectadas = new ArrayList<>();

        if (alertaDTO.isActiva() && esAlertaCritica(alertaDTO.getTipoAlerta())) {
            List<Atraccion> atracciones = atraccionRepository.findAll();

            for (Atraccion atraccion : atracciones) {
                if (debeCerrarPorClima(atraccion)) {
                    atraccion.setEstado(EstadoAtraccion.CERRADA);
                    atraccion.setMotivoCierre("Cierre por alerta climática: " + alertaDTO.getTipoAlerta().name());
                    atraccionRepository.save(atraccion);
                    atraccionesAfectadas.add(atraccion.getNombre());
                }
            }
        }

        return new AlertaClimaticaResponseDTO(
                "Alerta climática procesada correctamente",
                alertaDTO.getTipoAlerta().name(),
                atraccionesAfectadas.size(),
                atraccionesAfectadas
        );
    }

    private boolean esAlertaCritica(TipoAlertaClimatica tipoAlerta) {
        return tipoAlerta == TipoAlertaClimatica.LLUVIA_FUERTE
                || tipoAlerta == TipoAlertaClimatica.TORMENTA_ELECTRICA;
    }

    private boolean debeCerrarPorClima(Atraccion atraccion) {
        return atraccion.getTipo() == TipoAtraccion.ACUATICA
                || atraccion.getTipo() == TipoAtraccion.MECANICA_ALTURA;
    }
}
