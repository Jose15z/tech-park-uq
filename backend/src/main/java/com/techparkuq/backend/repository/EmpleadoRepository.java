package com.techparkuq.backend.repository;

import com.techparkuq.backend.enums.TipoEmpleado;
import com.techparkuq.backend.model.Empleado;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface EmpleadoRepository extends JpaRepository<Empleado, Long> {

    Optional<Empleado> findByDocumento(String documento);

    Optional<Empleado> findByEmail(String email);

    List<Empleado> findByTipoEmpleado(TipoEmpleado tipoEmpleado);

    List<Empleado> findByZonaAsignadaId(Long zonaId);

    List<Empleado> findByZonaAsignadaIdAndTipoEmpleado(Long zonaId, TipoEmpleado tipoEmpleado);
}
