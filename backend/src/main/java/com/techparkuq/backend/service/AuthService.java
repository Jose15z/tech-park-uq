package com.techparkuq.backend.service;

import com.techparkuq.backend.dto.LoginRequestDTO;
import com.techparkuq.backend.dto.LoginResponseDTO;
import com.techparkuq.backend.dto.RegistroVisitanteDTO;
import com.techparkuq.backend.enums.TipoEmpleado;
import com.techparkuq.backend.enums.TipoTicket;
import com.techparkuq.backend.exception.CredencialesInvalidasException;
import com.techparkuq.backend.exception.RecursoNoEncontradoException;
import com.techparkuq.backend.model.Empleado;
import com.techparkuq.backend.model.Ticket;
import com.techparkuq.backend.model.Visitante;
import com.techparkuq.backend.repository.EmpleadoRepository;
import com.techparkuq.backend.repository.TicketRepository;
import com.techparkuq.backend.repository.VisitanteRepository;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    private final VisitanteRepository visitanteRepository;
    private final EmpleadoRepository empleadoRepository;
    private final TicketRepository ticketRepository;

    public AuthService(VisitanteRepository visitanteRepository,
                       EmpleadoRepository empleadoRepository,
                       TicketRepository ticketRepository) {
        this.visitanteRepository = visitanteRepository;
        this.empleadoRepository = empleadoRepository;
        this.ticketRepository = ticketRepository;
    }

    public LoginResponseDTO login(LoginRequestDTO loginRequestDTO) {
        String email = loginRequestDTO.getEmail();
        String password = loginRequestDTO.getPassword();

        var visitanteOptional = visitanteRepository.findByEmail(email);

        if (visitanteOptional.isPresent()) {
            Visitante visitante = visitanteOptional.get();

            if (!visitante.getPassword().equals(password)) {
                throw new CredencialesInvalidasException("Credenciales inválidas");
            }

            return new LoginResponseDTO(
                    visitante.getId(),
                    visitante.getNombre(),
                    visitante.getEmail(),
                    "VISITANTE",
                    "Inicio de sesión exitoso"
            );
        }

        var empleadoOptional = empleadoRepository.findByEmail(email);

        if (empleadoOptional.isPresent()) {
            Empleado empleado = empleadoOptional.get();

            if (!empleado.getPassword().equals(password)) {
                throw new CredencialesInvalidasException("Credenciales inválidas");
            }

            String rol = empleado.getTipoEmpleado() == TipoEmpleado.ADMINISTRADOR
                    ? "ADMINISTRADOR"
                    : "OPERADOR";

            return new LoginResponseDTO(
                    empleado.getId(),
                    empleado.getNombre(),
                    empleado.getEmail(),
                    rol,
                    "Inicio de sesión exitoso"
            );
        }

        throw new CredencialesInvalidasException("No existe un usuario con ese correo");
    }

    public LoginResponseDTO registrarVisitante(RegistroVisitanteDTO registroDTO) {
        if (visitanteRepository.findByEmail(registroDTO.getEmail()).isPresent()
                || empleadoRepository.findByEmail(registroDTO.getEmail()).isPresent()) {
            throw new CredencialesInvalidasException("Ya existe una cuenta con este correo");
        }

        Ticket ticketGeneral = ticketRepository.save(new Ticket(TipoTicket.GENERAL, true));

        Visitante visitante = new Visitante(
                registroDTO.getNombre(),
                registroDTO.getDocumento(),
                registroDTO.getEmail(),
                registroDTO.getPassword(),
                registroDTO.getEdad(),
                registroDTO.getEstatura(),
                registroDTO.getSaldoVirtual(),
                registroDTO.getFotoUrl(),
                ticketGeneral
        );

        Visitante visitanteGuardado = visitanteRepository.save(visitante);

        return new LoginResponseDTO(
                visitanteGuardado.getId(),
                visitanteGuardado.getNombre(),
                visitanteGuardado.getEmail(),
                "VISITANTE",
                "Registro exitoso"
        );
    }
}