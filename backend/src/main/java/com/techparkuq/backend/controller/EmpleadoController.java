package com.techparkuq.backend.controller;

import com.techparkuq.backend.dto.AsignacionZonaDTO;
import com.techparkuq.backend.dto.ValidacionOperadorDTO;
import com.techparkuq.backend.dto.ValidacionOperadorResponseDTO;
import com.techparkuq.backend.model.Empleado;
import com.techparkuq.backend.service.EmpleadoService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/empleados")
@CrossOrigin(origins = "*")
public class EmpleadoController {

    private final EmpleadoService empleadoService;

    public EmpleadoController(EmpleadoService empleadoService) {
        this.empleadoService = empleadoService;
    }

    @GetMapping
    public List<Empleado> listarEmpleados() {
        return empleadoService.listarEmpleados();
    }

    @GetMapping("/{id}")
    public Empleado obtenerEmpleadoPorId(@PathVariable Long id) {
        return empleadoService.obtenerEmpleadoPorId(id);
    }

    @GetMapping("/documento/{documento}")
    public Empleado obtenerEmpleadoPorDocumento(@PathVariable String documento) {
        return empleadoService.obtenerEmpleadoPorDocumento(documento);
    }

    @GetMapping("/operadores")
    public List<Empleado> listarOperadores() {
        return empleadoService.listarOperadores();
    }

    @GetMapping("/administradores")
    public List<Empleado> listarAdministradores() {
        return empleadoService.listarAdministradores();
    }

    @GetMapping("/zona/{zonaId}")
    public List<Empleado> listarEmpleadosPorZona(@PathVariable Long zonaId) {
        return empleadoService.listarEmpleadosPorZona(zonaId);
    }

    @GetMapping("/zona/{zonaId}/operadores")
    public List<Empleado> listarOperadoresPorZona(@PathVariable Long zonaId) {
        return empleadoService.listarOperadoresPorZona(zonaId);
    }

    @PostMapping
    public Empleado crearEmpleado(@RequestBody Empleado empleado) {
        return empleadoService.guardarEmpleado(empleado);
    }

    @PutMapping("/{id}")
    public Empleado actualizarEmpleado(@PathVariable Long id, @RequestBody Empleado empleado) {
        return empleadoService.actualizarEmpleado(id, empleado);
    }

    @PutMapping("/asignar-zona")
    public Empleado asignarEmpleadoAZona(@RequestBody AsignacionZonaDTO asignacionZonaDTO) {
        return empleadoService.asignarEmpleadoAZona(
                asignacionZonaDTO.getEmpleadoId(),
                asignacionZonaDTO.getZonaId()
        );
    }

    @PutMapping("/{empleadoId}/quitar-zona")
    public Empleado quitarZonaAsignada(@PathVariable Long empleadoId) {
        return empleadoService.quitarZonaAsignada(empleadoId);
    }

    @PostMapping("/validar-gestion")
    public ValidacionOperadorResponseDTO validarOperadorGestionaAtraccion(
            @RequestBody ValidacionOperadorDTO validacionOperadorDTO) {
        return empleadoService.validarOperadorGestionaAtraccion(
                validacionOperadorDTO.getOperadorId(),
                validacionOperadorDTO.getAtraccionId()
        );
    }

    @DeleteMapping("/{id}")
    public void eliminarEmpleado(@PathVariable Long id) {
        empleadoService.eliminarEmpleado(id);
    }
}