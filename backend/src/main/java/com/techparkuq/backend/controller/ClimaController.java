package com.techparkuq.backend.controller;

import com.techparkuq.backend.dto.AlertaClimaticaDTO;
import com.techparkuq.backend.dto.AlertaClimaticaResponseDTO;
import com.techparkuq.backend.service.ClimaService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/clima")
@CrossOrigin(origins = "*")
public class ClimaController {

    private final ClimaService climaService;

    public ClimaController(ClimaService climaService) {
        this.climaService = climaService;
    }

    @PostMapping("/alerta")
    public AlertaClimaticaResponseDTO activarAlerta(@RequestBody AlertaClimaticaDTO alertaDTO) {
        return climaService.procesarAlerta(alertaDTO);
    }
}
