package com.techparkuq.backend.controller;

import com.techparkuq.backend.dto.DatosDemoResponseDTO;
import com.techparkuq.backend.service.DatosDemoService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/datos")
@CrossOrigin(origins = "*")
public class DatosDemoController {

    private final DatosDemoService datosDemoService;

    public DatosDemoController(DatosDemoService datosDemoService) {
        this.datosDemoService = datosDemoService;
    }

    @PostMapping("/cargar-demo")
    public DatosDemoResponseDTO cargarDatosDemo() {
        return datosDemoService.cargarDatosDemo();
    }
}