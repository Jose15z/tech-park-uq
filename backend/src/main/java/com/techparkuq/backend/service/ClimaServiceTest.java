package com.techparkuq.backend.service;

import com.techparkuq.backend.dto.AlertaClimaticaDTO;
import com.techparkuq.backend.dto.AlertaClimaticaResponseDTO;
import com.techparkuq.backend.enums.EstadoAtraccion;
import com.techparkuq.backend.enums.TipoAlertaClimatica;
import com.techparkuq.backend.enums.TipoAtraccion;
import com.techparkuq.backend.model.AlertaClimatica;
import com.techparkuq.backend.model.Atraccion;
import com.techparkuq.backend.model.Zona;
import com.techparkuq.backend.repository.AlertaClimaticaRepository;
import com.techparkuq.backend.repository.AtraccionRepository;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ClimaServiceTest {

    @Test
    void debeCerrarAtraccionesAcuaticasYMecanicasAnteLluviaFuerte() {
        AtraccionRepository atraccionRepository = mock(AtraccionRepository.class);
        AlertaClimaticaRepository alertaClimaticaRepository = mock(AlertaClimaticaRepository.class);

        ClimaService climaService = new ClimaService(atraccionRepository, alertaClimaticaRepository);

        Zona zona = new Zona("Zona Demo", 200);

        Atraccion acuatica = new Atraccion(
                "Río Salvaje",
                TipoAtraccion.ACUATICA,
                30,
                1.20,
                8,
                0,
                0,
                10,
                EstadoAtraccion.ACTIVA,
                null,
                zona
        );

        Atraccion mecanicaAltura = new Atraccion(
                "Montaña Rusa X",
                TipoAtraccion.MECANICA_ALTURA,
                20,
                1.40,
                12,
                5000,
                0,
                15,
                EstadoAtraccion.ACTIVA,
                null,
                zona
        );

        Atraccion infantil = new Atraccion(
                "Carrusel Feliz",
                TipoAtraccion.INFANTIL,
                25,
                0.90,
                3,
                0,
                0,
                5,
                EstadoAtraccion.ACTIVA,
                null,
                zona
        );

        when(atraccionRepository.findAll()).thenReturn(List.of(acuatica, mecanicaAltura, infantil));
        when(alertaClimaticaRepository.save(any(AlertaClimatica.class)))
                .thenAnswer(invocation -> invocation.getArgument(0));
        when(atraccionRepository.save(any(Atraccion.class)))
                .thenAnswer(invocation -> invocation.getArgument(0));

        AlertaClimaticaDTO alertaDTO = new AlertaClimaticaDTO(
                TipoAlertaClimatica.LLUVIA_FUERTE,
                true
        );

        AlertaClimaticaResponseDTO respuesta = climaService.procesarAlerta(alertaDTO);

        assertEquals(EstadoAtraccion.CERRADA, acuatica.getEstado());
        assertEquals(EstadoAtraccion.CERRADA, mecanicaAltura.getEstado());
        assertEquals(EstadoAtraccion.ACTIVA, infantil.getEstado());

        assertEquals(2, respuesta.getCantidadAtraccionesCerradas());
        assertTrue(respuesta.getAtraccionesAfectadas().contains("Río Salvaje"));
        assertTrue(respuesta.getAtraccionesAfectadas().contains("Montaña Rusa X"));

        verify(atraccionRepository, times(2)).save(any(Atraccion.class));
    }

    @Test
    void noDebeCerrarAtraccionesCuandoAlertaNoEstaActiva() {
        AtraccionRepository atraccionRepository = mock(AtraccionRepository.class);
        AlertaClimaticaRepository alertaClimaticaRepository = mock(AlertaClimaticaRepository.class);

        ClimaService climaService = new ClimaService(atraccionRepository, alertaClimaticaRepository);

        AlertaClimaticaDTO alertaDTO = new AlertaClimaticaDTO(
                TipoAlertaClimatica.LLUVIA_FUERTE,
                false
        );

        when(alertaClimaticaRepository.save(any(AlertaClimatica.class)))
                .thenAnswer(invocation -> invocation.getArgument(0));

        AlertaClimaticaResponseDTO respuesta = climaService.procesarAlerta(alertaDTO);

        assertEquals(0, respuesta.getCantidadAtraccionesCerradas());

        verify(atraccionRepository, never()).findAll();
        verify(atraccionRepository, never()).save(any(Atraccion.class));
    }
}
