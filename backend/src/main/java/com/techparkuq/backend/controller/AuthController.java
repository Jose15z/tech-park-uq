package com.techparkuq.backend.controller;

import com.techparkuq.backend.dto.LoginRequestDTO;
import com.techparkuq.backend.dto.LoginResponseDTO;
import com.techparkuq.backend.dto.RegistroVisitanteDTO;
import com.techparkuq.backend.service.AuthService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin(origins = "*")
public class AuthController {

    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/login")
    public LoginResponseDTO login(@RequestBody LoginRequestDTO loginRequestDTO) {
        return authService.login(loginRequestDTO);
    }

    @PostMapping("/registro/visitante")
    public LoginResponseDTO registrarVisitante(@RequestBody RegistroVisitanteDTO registroDTO) {
        return authService.registrarVisitante(registroDTO);
    }
}