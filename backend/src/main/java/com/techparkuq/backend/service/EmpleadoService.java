import com.techparkuq.backend.dto.ValidacionOperadorResponseDTO;
import com.techparkuq.backend.enums.TipoEmpleado;
import com.techparkuq.backend.exception.AsignacionInvalidaException;
import com.techparkuq.backend.exception.RecursoNoEncontradoException;
import com.techparkuq.backend.model.Atraccion;
import com.techparkuq.backend.model.Empleado;
import com.techparkuq.backend.model.Zona;
import com.techparkuq.backend.repository.EmpleadoRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmpleadoService {

    private final EmpleadoRepository empleadoRepository;
    private final ZonaService zonaService;
    private final AtraccionService atraccionService;

    public EmpleadoService(EmpleadoRepository empleadoRepository,
                           ZonaService zonaService,
                           AtraccionService atraccionService) {
        this.empleadoRepository = empleadoRepository;
        this.zonaService = zonaService;
        this.atraccionService = atraccionService;
    }

    public List<Empleado> listarEmpleados() {
        return empleadoRepository.findAll();
    }

    public Empleado obtenerEmpleadoPorId(Long id) {
        return empleadoRepository.findById(id)
                .orElseThrow(() -> new RecursoNoEncontradoException("Empleado no encontrado con id: " + id));
    }

    public Empleado obtenerEmpleadoPorDocumento(String documento) {
        return empleadoRepository.findByDocumento(documento)
                .orElseThrow(() -> new RecursoNoEncontradoException("Empleado no encontrado con documento: " + documento));
    }

    public List<Empleado> listarOperadores() {
        return empleadoRepository.findByTipoEmpleado(TipoEmpleado.OPERADOR);
    }

    public List<Empleado> listarAdministradores() {
        return empleadoRepository.findByTipoEmpleado(TipoEmpleado.ADMINISTRADOR);
    }

    public List<Empleado> listarEmpleadosPorZona(Long zonaId) {
        zonaService.obtenerZonaPorId(zonaId);
        return empleadoRepository.findByZonaAsignadaId(zonaId);
    }

    public List<Empleado> listarOperadoresPorZona(Long zonaId) {
        zonaService.obtenerZonaPorId(zonaId);
        return empleadoRepository.findByZonaAsignadaIdAndTipoEmpleado(zonaId, TipoEmpleado.OPERADOR);
    }

    public Empleado guardarEmpleado(Empleado empleado) {
        if (empleado.getZonaAsignada() != null && empleado.getZonaAsignada().getId() != null) {
            Zona zona = zonaService.obtenerZonaPorId(empleado.getZonaAsignada().getId());
            empleado.setZonaAsignada(zona);
        }

        return empleadoRepository.save(empleado);
    }

    public Empleado actualizarEmpleado(Long id, Empleado empleadoActualizado) {
        Empleado empleadoExistente = obtenerEmpleadoPorId(id);

        empleadoExistente.setNombre(empleadoActualizado.getNombre());
        empleadoExistente.setDocumento(empleadoActualizado.getDocumento());
        empleadoExistente.setEmail(empleadoActualizado.getEmail());
        empleadoExistente.setPassword(empleadoActualizado.getPassword());
        empleadoExistente.setTipoEmpleado(empleadoActualizado.getTipoEmpleado());

        if (empleadoActualizado.getZonaAsignada() != null && empleadoActualizado.getZonaAsignada().getId() != null) {
            Zona zona = zonaService.obtenerZonaPorId(empleadoActualizado.getZonaAsignada().getId());
            empleadoExistente.setZonaAsignada(zona);
        } else {
            empleadoExistente.setZonaAsignada(null);
        }

        return empleadoRepository.save(empleadoExistente);
    }

    public Empleado asignarEmpleadoAZona(Long empleadoId, Long zonaId) {
        Empleado empleado = obtenerEmpleadoPorId(empleadoId);
        Zona zona = zonaService.obtenerZonaPorId(zonaId);

        empleado.setZonaAsignada(zona);

        return empleadoRepository.save(empleado);
    }

    public Empleado quitarZonaAsignada(Long empleadoId) {
        Empleado empleado = obtenerEmpleadoPorId(empleadoId);

        empleado.setZonaAsignada(null);

        return empleadoRepository.save(empleado);
    }

    public void eliminarEmpleado(Long id) {
        Empleado empleado = obtenerEmpleadoPorId(id);
        empleadoRepository.delete(empleado);
    }

    public ValidacionOperadorResponseDTO validarOperadorGestionaAtraccion(Long operadorId, Long atraccionId) {
        Empleado operador = obtenerEmpleadoPorId(operadorId);
        Atraccion atraccion = atraccionService.obtenerAtraccionPorId(atraccionId);

        if (operador.getTipoEmpleado() != TipoEmpleado.OPERADOR) {
            throw new AsignacionInvalidaException("El empleado no es un operador");
        }

        if (operador.getZonaAsignada() == null) {
            throw new AsignacionInvalidaException("El operador no tiene una zona asignada");
        }

        if (atraccion.getZona() == null) {
            throw new AsignacionInvalidaException("La atracción no tiene una zona asociada");
        }

        Long zonaOperadorId = operador.getZonaAsignada().getId();
        Long zonaAtraccionId = atraccion.getZona().getId();

        boolean autorizado = zonaOperadorId.equals(zonaAtraccionId);

        if (!autorizado) {
            throw new AsignacionInvalidaException(
                    "El operador no puede gestionar esta atracción porque pertenece a otra zona"
            );
        }

        return new ValidacionOperadorResponseDTO(
                "El operador está autorizado para gestionar esta atracción",
                true,
                operador.getNombre(),
                atraccion.getNombre(),
                atraccion.getZona().getNombre()
        );
    }
}