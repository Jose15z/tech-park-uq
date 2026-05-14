package com.techparkuq.backend.service;

import com.techparkuq.backend.dto.DatosDemoResponseDTO;
import com.techparkuq.backend.enums.EstadoAtraccion;
import com.techparkuq.backend.enums.TipoAtraccion;
import com.techparkuq.backend.enums.TipoEmpleado;
import com.techparkuq.backend.enums.TipoTicket;
import com.techparkuq.backend.model.Atraccion;
import com.techparkuq.backend.model.Empleado;
import com.techparkuq.backend.model.Ticket;
import com.techparkuq.backend.model.Visitante;
import com.techparkuq.backend.model.Zona;
import com.techparkuq.backend.repository.AtraccionRepository;
import com.techparkuq.backend.repository.EmpleadoRepository;
import com.techparkuq.backend.repository.TicketRepository;
import com.techparkuq.backend.repository.VisitanteRepository;
import com.techparkuq.backend.repository.ZonaRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class DatosDemoService {

    private final ZonaRepository zonaRepository;
    private final AtraccionRepository atraccionRepository;
    private final TicketRepository ticketRepository;
    private final VisitanteRepository visitanteRepository;
    private final EmpleadoRepository empleadoRepository;
    private final RutaService rutaService;

    public DatosDemoService(ZonaRepository zonaRepository,
                            AtraccionRepository atraccionRepository,
                            TicketRepository ticketRepository,
                            VisitanteRepository visitanteRepository,
                            EmpleadoRepository empleadoRepository,
                            RutaService rutaService) {
        this.zonaRepository = zonaRepository;
        this.atraccionRepository = atraccionRepository;
        this.ticketRepository = ticketRepository;
        this.visitanteRepository = visitanteRepository;
        this.empleadoRepository = empleadoRepository;
        this.rutaService = rutaService;
    }

    @Transactional
    public DatosDemoResponseDTO cargarDatosDemo() {
        if (yaExistenDatos()) {
            return new DatosDemoResponseDTO(
                    "El escenario demo no se cargó porque ya existen datos en la base de datos.",
                    0,
                    0,
                    0,
                    0,
                    0,
                    List.of("Para evitar duplicados, el sistema bloqueó la carga repetida del escenario demo.")
            );
        }

        List<String> resumen = new ArrayList<>();

        Zona zonaExtrema = zonaRepository.save(new Zona("Zona Extrema", 250));
        Zona zonaAcuatica = zonaRepository.save(new Zona("Zona Acuática", 200));
        Zona zonaFamiliar = zonaRepository.save(new Zona("Zona Familiar", 300));

        resumen.add("Zonas creadas: Zona Extrema, Zona Acuática y Zona Familiar.");

        Atraccion montanaRusa = crearAtraccion(
                "Montaña Rusa X",
                TipoAtraccion.MECANICA_ALTURA,
                24,
                1.40,
                12,
                5000,
                15,
                zonaExtrema
        );

        Atraccion torreCaida = crearAtraccion(
                "Torre de Caída",
                TipoAtraccion.MECANICA_ALTURA,
                16,
                1.45,
                14,
                7000,
                20,
                zonaExtrema
        );

        Atraccion rioSalvaje = crearAtraccion(
                "Río Salvaje",
                TipoAtraccion.ACUATICA,
                30,
                1.20,
                8,
                0,
                10,
                zonaAcuatica
        );

        Atraccion splash = crearAtraccion(
                "Splash UQ",
                TipoAtraccion.ACUATICA,
                20,
                1.10,
                7,
                0,
                12,
                zonaAcuatica
        );

        Atraccion carrusel = crearAtraccion(
                "Carrusel Feliz",
                TipoAtraccion.INFANTIL,
                25,
                0.90,
                3,
                0,
                5,
                zonaFamiliar
        );

        Atraccion rueda = crearAtraccion(
                "Rueda Panorámica",
                TipoAtraccion.FAMILIAR,
                32,
                1.00,
                5,
                0,
                8,
                zonaFamiliar
        );

        resumen.add("Atracciones creadas: Montaña Rusa X, Torre de Caída, Río Salvaje, Splash UQ, Carrusel Feliz y Rueda Panorámica.");

        Ticket ticketGeneral = ticketRepository.save(new Ticket(TipoTicket.GENERAL, true));
        Ticket ticketFastPass = ticketRepository.save(new Ticket(TipoTicket.FAST_PASS, true));
        Ticket ticketFamiliar = ticketRepository.save(new Ticket(TipoTicket.FAMILIAR, true));

        resumen.add("Tickets creados: General, Fast-Pass y Familiar.");

        visitanteRepository.save(new Visitante(
                "Laura Gómez",
                "100000001",
                16,
                1.62,
                30000,
                "https://example.com/laura.jpg",
                ticketGeneral
        ));

        visitanteRepository.save(new Visitante(
                "Carlos Ramírez",
                "100000002",
                21,
                1.78,
                50000,
                "https://example.com/carlos.jpg",
                ticketFastPass
        ));

        visitanteRepository.save(new Visitante(
                "Sofía Martínez",
                "100000003",
                10,
                1.25,
                15000,
                "https://example.com/sofia.jpg",
                ticketFamiliar
        ));

        resumen.add("Visitantes demo creados: Laura Gómez, Carlos Ramírez y Sofía Martínez.");

        empleadoRepository.save(new Empleado(
                "Andrés López",
                "200000001",
                "andres@techpark.com",
                "1234",
                TipoEmpleado.OPERADOR,
                zonaExtrema
        ));

        empleadoRepository.save(new Empleado(
                "Valentina Ruiz",
                "200000002",
                "valentina@techpark.com",
                "1234",
                TipoEmpleado.OPERADOR,
                zonaAcuatica
        ));

        empleadoRepository.save(new Empleado(
                "María Admin",
                "200000003",
                "admin@techpark.com",
                "admin123",
                TipoEmpleado.ADMINISTRADOR,
                null
        ));

        resumen.add("Empleados demo creados: dos operadores y una administradora.");

        conectarGrafoDemo(montanaRusa, torreCaida, rioSalvaje, splash, carrusel, rueda);

        resumen.add("Grafo demo conectado con senderos entre atracciones.");

        return new DatosDemoResponseDTO(
                "Escenario demo cargado correctamente.",
                3,
                6,
                3,
                3,
                3,
                resumen
        );
    }

    private boolean yaExistenDatos() {
        return zonaRepository.count() > 0
                || atraccionRepository.count() > 0
                || visitanteRepository.count() > 0
                || empleadoRepository.count() > 0;
    }

    private Atraccion crearAtraccion(String nombre,
                                     TipoAtraccion tipo,
                                     int capacidadMaximaPorCiclo,
                                     double alturaMinima,
                                     int edadMinima,
                                     double costoAdicional,
                                     int tiempoEspera,
                                     Zona zona) {
        Atraccion atraccion = new Atraccion(
                nombre,
                tipo,
                capacidadMaximaPorCiclo,
                alturaMinima,
                edadMinima,
                costoAdicional,
                0,
                tiempoEspera,
                EstadoAtraccion.ACTIVA,
                null,
                zona
        );

        return atraccionRepository.save(atraccion);
    }

    private void conectarGrafoDemo(Atraccion montanaRusa,
                                   Atraccion torreCaida,
                                   Atraccion rioSalvaje,
                                   Atraccion splash,
                                   Atraccion carrusel,
                                   Atraccion rueda) {
        rutaService.conectarAtracciones(montanaRusa.getId(), torreCaida.getId(), 4);
        rutaService.conectarAtracciones(torreCaida.getId(), rioSalvaje.getId(), 7);
        rutaService.conectarAtracciones(rioSalvaje.getId(), splash.getId(), 3);
        rutaService.conectarAtracciones(splash.getId(), carrusel.getId(), 6);
        rutaService.conectarAtracciones(carrusel.getId(), rueda.getId(), 2);
        rutaService.conectarAtracciones(montanaRusa.getId(), rueda.getId(), 15);
        rutaService.conectarAtracciones(rioSalvaje.getId(), rueda.getId(), 8);
    }
}