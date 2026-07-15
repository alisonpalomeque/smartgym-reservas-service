package ec.edu.ucacue.smartgym.service.impl;

import ec.edu.ucacue.smartgym.dto.ReservaLugarResponse;
import ec.edu.ucacue.smartgym.dto.PersonaResponse;
import ec.edu.ucacue.smartgym.dto.ReservaLugarDTO;
import ec.edu.ucacue.smartgym.dto.ReservaLugarRequest;
import ec.edu.ucacue.smartgym.client.PersonaClient;
import ec.edu.ucacue.smartgym.entity.ReservaLugar;
import ec.edu.ucacue.smartgym.entity.Lugar;
import ec.edu.ucacue.smartgym.enums.EstadoReserva;
import ec.edu.ucacue.smartgym.repository.ReservaLugarRepository;
import ec.edu.ucacue.smartgym.repository.LugarRepository;
import ec.edu.ucacue.smartgym.service.ReservaLugarService;

import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor // Esto crea el constructor por ti. ¡NO pongas constructor manual abajo!
public class ReservaLugarServiceImpl implements ReservaLugarService {

    private static final Logger log = LoggerFactory.getLogger(ReservaLugarServiceImpl.class);

    private final ReservaLugarRepository reservaRepository;
    private final LugarRepository lugarRepository;
    private final PersonaClient personaClient;
    
    @Override
    public ReservaLugarResponse crearReserva(ReservaLugarRequest request) {
        if (request.getUsu_id() == null || request.getLug_id() == null) {
            throw new RuntimeException("usu_id y lug_id son obligatorios");
        }

        log.info("Creando reserva: usu_id={}, lug_id={}", request.getUsu_id(), request.getLug_id());

        // Validar persona en microservicio 3003
        try {
            var persona = personaClient.obtenerPersona(request.getUsu_id());
            if (persona == null) throw new RuntimeException("No existe persona con ID: " + request.getUsu_id());
        } catch (Exception e) {
            throw new RuntimeException("Error en servicio de personas: " + e.getMessage());
        }

        Lugar lugar = lugarRepository.findById(request.getLug_id())
                .orElseThrow(() -> new RuntimeException("Lugar no encontrado: " + request.getLug_id()));

        if (lugar.getLug_cupos_disponibles() <= 0) {
            throw new RuntimeException("Sin cupos en: " + lugar.getLug_nombre());
        }

        lugar.setLug_cupos_disponibles(lugar.getLug_cupos_disponibles() - 1);
        lugarRepository.save(lugar);

        ReservaLugar reserva = ReservaLugar.builder()
                .usu_id(request.getUsu_id())
                .lug_id(request.getLug_id())
                .res_lug_estado(EstadoReserva.PENDIENTE)
                .res_lug_fecha_creacion(LocalDateTime.now())
                .build();

        ReservaLugar saved = reservaRepository.save(reserva);

        return ReservaLugarResponse.builder()
                .res_lug_id(saved.getRes_lug_id())
                .usu_id(saved.getUsu_id())
                .lug_id(saved.getLug_id())
                .res_lug_estado(saved.getRes_lug_estado())
                .res_lug_fecha_creacion(saved.getRes_lug_fecha_creacion())
                .mensaje("Reserva realizada con éxito")
                .build();
    }

   @Override
public ReservaLugarResponse buscarPorId(Long id) {
    // 1. Buscas la reserva en tu base de datos (esto ya lo hace tu código)
    ReservaLugar reserva = reservaRepository.findById(id)
        .orElseThrow(() -> new RuntimeException("Reserva no encontrada"));

    // 2. Aquí está la clave: Llamas al cliente para traer los datos de la persona
    PersonaResponse persona = personaClient.obtenerPersona(reserva.getUsu_id());

    // 3. Creas el objeto de respuesta y le asignas los datos de la persona
    ReservaLugarResponse response = new ReservaLugarResponse();
    response.setRes_lug_id(reserva.getRes_lug_id());
    // ... asigna el resto de campos que tengas ...
    
    // AQUÍ ESTÁ EL TRUCO PARA QUE NO SEA NULL:
    response.setPersona(persona); 

    return response;
}

    @Override
public List<ReservaLugarResponse> listarTodas() {
    return reservaRepository.findAll().stream()
        .map(reserva -> {
            // Buscamos la persona por cada reserva
            PersonaResponse persona = null;
            try {
                persona = personaClient.obtenerPersona(reserva.getUsu_id());
            } catch (Exception e) {
                log.error("Error al obtener persona para reserva {}: {}", reserva.getRes_lug_id(), e.getMessage());
            }
            
            // Construimos la respuesta incluyendo la persona
            return ReservaLugarResponse.builder()
                .res_lug_id(reserva.getRes_lug_id())
                .usu_id(reserva.getUsu_id())
                .lug_id(reserva.getLug_id())
                .res_lug_estado(reserva.getRes_lug_estado())
                .res_lug_fecha_creacion(reserva.getRes_lug_fecha_creacion())
                .persona(persona) // <--- ¡Esto faltaba!
                .mensaje("Reserva listada")
                .build();
        })
        .collect(Collectors.toList());
}

    @Override
    public ReservaLugarDTO obtenerReservaConPersona(Long id) {
        // 1. Obtener de tu BD
        ReservaLugar reserva = reservaRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Reserva no encontrada con ID: " + id));

        // 2. Obtener la persona del OTRO servicio (3001)
        PersonaResponse persona = null;
        try {
            persona = personaClient.obtenerPersona(reserva.getUsu_id());
        } catch (Exception e) {
            log.error("Error al obtener persona del servicio externo: {}", e.getMessage());
        }

        // 3. Crear el DTO y unir todo
        ReservaLugarDTO dto = new ReservaLugarDTO();
        dto.setRes_lug_id(reserva.getRes_lug_id());
        dto.setUsu_id(reserva.getUsu_id());
        dto.setPersona(persona); 

        return dto;
    }
}