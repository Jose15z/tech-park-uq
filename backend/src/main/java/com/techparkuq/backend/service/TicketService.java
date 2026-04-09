package com.techparkuq.backend.service;

import com.techparkuq.backend.exception.RecursoNoEncontradoException;
import com.techparkuq.backend.model.Ticket;
import com.techparkuq.backend.repository.TicketRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TicketService {

    private final TicketRepository ticketRepository;

    public TicketService(TicketRepository ticketRepository) {
        this.ticketRepository = ticketRepository;
    }

    public List<Ticket> listarTickets() {
        return ticketRepository.findAll();
    }

    public Ticket obtenerTicketPorId(Long id) {
        return ticketRepository.findById(id)
                .orElseThrow(() -> new RecursoNoEncontradoException("Ticket no encontrado con id: " + id));
    }

    public Ticket guardarTicket(Ticket ticket) {
        return ticketRepository.save(ticket);
    }

    public Ticket actualizarTicket(Long id, Ticket ticketActualizado) {
        Ticket ticketExistente = obtenerTicketPorId(id);

        ticketExistente.setTipo(ticketActualizado.getTipo());
        ticketExistente.setActivo(ticketActualizado.isActivo());

        return ticketRepository.save(ticketExistente);
    }

    public void eliminarTicket(Long id) {
        Ticket ticket = obtenerTicketPorId(id);
        ticketRepository.delete(ticket);
    }
}