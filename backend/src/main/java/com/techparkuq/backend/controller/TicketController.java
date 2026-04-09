package com.techparkuq.backend.controller;

import com.techparkuq.backend.model.Ticket;
import com.techparkuq.backend.service.TicketService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/tickets")
@CrossOrigin(origins = "*")
public class TicketController {

    private final TicketService ticketService;

    public TicketController(TicketService ticketService) {
        this.ticketService = ticketService;
    }

    @GetMapping
    public List<Ticket> listarTickets() {
        return ticketService.listarTickets();
    }

    @GetMapping("/{id}")
    public Ticket obtenerTicketPorId(@PathVariable Long id) {
        return ticketService.obtenerTicketPorId(id);
    }

    @PostMapping
    public Ticket crearTicket(@RequestBody Ticket ticket) {
        return ticketService.guardarTicket(ticket);
    }

    @PutMapping("/{id}")
    public Ticket actualizarTicket(@PathVariable Long id, @RequestBody Ticket ticket) {
        return ticketService.actualizarTicket(id, ticket);
    }

    @DeleteMapping("/{id}")
    public void eliminarTicket(@PathVariable Long id) {
        ticketService.eliminarTicket(id);
    }
}