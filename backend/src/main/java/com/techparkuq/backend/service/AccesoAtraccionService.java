package com.techparkuq.backend.service;

import com.techparkuq.backend.dto.AccesoAtraccionResponseDTO;
import com.techparkuq.backend.enums.EstadoAtraccion;
import com.techparkuq.backend.enums.TipoTicket;
import com.techparkuq.backend.exception.AtraccionNoDisponibleException;
import com.techparkuq.backend.exception.RestriccionSeguridadException;
import com.techparkuq.backend.exception.SaldoInsuficienteException;
import com.techparkuq.backend.model.Atraccion;
import com.techparkuq.backend.model.Ticket;
import com.techparkuq.backend.model.Visitante;
import com.techparkuq.backend.repository.AtraccionRepository;
import com.techparkuq.backend.repository.VisitanteRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.techparkuq.backend.model.RegistroVisita;
import java.time.LocalDateTime;

@Service
public class AccesoAtraccionService {

    private static final int LIMITE_MANTENIMIENTO = 500;

    private final VisitanteService visitanteService;
    private final AtraccionService atraccionService;
    private final VisitanteRepository visitanteRepository;
    private final AtraccionRepository atraccionRepository;
    private final HistorialVisitasService historialVisitasService;

    public AccesoAtraccionService(VisitanteService visitanteService,
                                  AtraccionService atraccionService,
                                  VisitanteRepository visitanteRepository,
                                  AtraccionRepository atraccionRepository,
                                  HistorialVisitasService historialVisitasService) {
        this.visitanteService = visitanteService;
        this.atraccionService = atraccionService;
        this.visitanteRepository = visitanteRepository;
        this.atraccionRepository = atraccionRepository;
        this.historialVisitasService = historialVisitasService;
    }

    @Transactional
    public AccesoAtraccionResponseDTO procesarIngreso(Long visitanteId, Long atraccionId) {
        Visitante visitante = visitanteService.obtenerVisitantePorId(visitanteId);
        Atraccion atraccion = atraccionService.obtenerAtraccionPorId(atraccionId);

        validarDisponibilidadAtraccion(atraccion);
        validarRestriccionesSeguridad(visitante, atraccion);
        procesarCobroSiAplica(visitante, atraccion);

        atraccion.setVisitantesAcumulados(atraccion.getVisitantesAcumulados() + 1);

        if (atraccion.getVisitantesAcumulados() >= LIMITE_MANTENIMIENTO) {
            atraccion.setEstado(EstadoAtraccion.EN_MANTENIMIENTO);
            atraccion.setMotivoCierre("Bloqueo automático por mantenimiento preventivo al alcanzar 500 visitantes");
        }

        visitanteRepository.save(visitante);
        atraccionRepository.save(atraccion);
        historialVisitasService.registrarVisita(
                new RegistroVisita(
                        visitante.getId(),
                        visitante.getNombre(),
                        atraccion.getId(),
                        atraccion.getNombre(),
                        LocalDateTime.now()
                )
        );



        return new AccesoAtraccionResponseDTO(
                "Ingreso autorizado correctamente",
                visitante.getNombre(),
                atraccion.getNombre(),
                visitante.getSaldoVirtual(),
                atraccion.getVisitantesAcumulados(),
                atraccion.getEstado().name()
        );
    }

    private void validarDisponibilidadAtraccion(Atraccion atraccion) {
        if (atraccion.getEstado() == EstadoAtraccion.CERRADA) {
            throw new AtraccionNoDisponibleException("La atracción está cerrada");
        }

        if (atraccion.getEstado() == EstadoAtraccion.EN_MANTENIMIENTO) {
            throw new AtraccionNoDisponibleException("La atracción está en mantenimiento");
        }
    }

    private void validarRestriccionesSeguridad(Visitante visitante, Atraccion atraccion) {
        if (visitante.getEdad() < atraccion.getEdadMinima()) {
            throw new RestriccionSeguridadException(
                    "El visitante no cumple con la edad mínima requerida para esta atracción"
            );
        }

        if (visitante.getEstatura() < atraccion.getAlturaMinima()) {
            throw new RestriccionSeguridadException(
                    "El visitante no cumple con la estatura mínima requerida para esta atracción"
            );
        }
    }

    private void procesarCobroSiAplica(Visitante visitante, Atraccion atraccion) {
        Ticket ticket = visitante.getTicket();

        if (ticket == null || !ticket.isActivo()) {
            throw new AtraccionNoDisponibleException("El visitante no tiene un ticket activo");
        }

        if (ticket.getTipo() == TipoTicket.GENERAL && atraccion.getCostoAdicional() > 0) {
            if (visitante.getSaldoVirtual() < atraccion.getCostoAdicional()) {
                throw new SaldoInsuficienteException("El visitante no tiene saldo suficiente para ingresar");
            }

            visitante.setSaldoVirtual(visitante.getSaldoVirtual() - atraccion.getCostoAdicional());
        }
    }
}