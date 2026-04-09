package com.techparkuq.backend.service;

import com.techparkuq.backend.exception.RecursoNoEncontradoException;
import com.techparkuq.backend.model.Ticket;
import com.techparkuq.backend.model.Visitante;
import com.techparkuq.backend.repository.VisitanteRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VisitanteService {

    private final VisitanteRepository visitanteRepository;
    private final TicketService ticketService;

    public VisitanteService(VisitanteRepository visitanteRepository, TicketService ticketService) {
        this.visitanteRepository = visitanteRepository;
        this.ticketService = ticketService;
    }

    public List<Visitante> listarVisitantes() {
        return visitanteRepository.findAll();
    }

    public Visitante obtenerVisitantePorId(Long id) {
        return visitanteRepository.findById(id)
                .orElseThrow(() -> new RecursoNoEncontradoException("Visitante no encontrado con id: " + id));
    }

    public Visitante obtenerVisitantePorDocumento(String documento) {
        return visitanteRepository.findByDocumento(documento)
                .orElseThrow(() -> new RecursoNoEncontradoException("Visitante no encontrado con documento: " + documento));
    }

    public Visitante guardarVisitante(Visitante visitante) {
        if (visitante.getTicket() != null && visitante.getTicket().getId() != null) {
            Ticket ticket = ticketService.obtenerTicketPorId(visitante.getTicket().getId());
            visitante.setTicket(ticket);
        }

        return visitanteRepository.save(visitante);
    }

    public Visitante actualizarVisitante(Long id, Visitante visitanteActualizado) {
        Visitante visitanteExistente = obtenerVisitantePorId(id);

        visitanteExistente.setNombre(visitanteActualizado.getNombre());
        visitanteExistente.setDocumento(visitanteActualizado.getDocumento());
        visitanteExistente.setEdad(visitanteActualizado.getEdad());
        visitanteExistente.setEstatura(visitanteActualizado.getEstatura());
        visitanteExistente.setSaldoVirtual(visitanteActualizado.getSaldoVirtual());
        visitanteExistente.setFotoUrl(visitanteActualizado.getFotoUrl());

        if (visitanteActualizado.getTicket() != null && visitanteActualizado.getTicket().getId() != null) {
            Ticket ticket = ticketService.obtenerTicketPorId(visitanteActualizado.getTicket().getId());
            visitanteExistente.setTicket(ticket);
        } else {
            visitanteExistente.setTicket(null);
        }

        return visitanteRepository.save(visitanteExistente);
    }

    public void eliminarVisitante(Long id) {
        Visitante visitante = obtenerVisitantePorId(id);
        visitanteRepository.delete(visitante);
    }
}