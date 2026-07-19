package ec.edu.ucacue.smartgym.service.impl;

import ec.edu.ucacue.smartgym.dto.*;
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
@RequiredArgsConstructor
public class ReservaLugarServiceImpl implements ReservaLugarService {

    private static final Logger log = LoggerFactory.getLogger(ReservaLugarServiceImpl.class);

    private final ReservaLugarRepository reservaRepository;
    private final LugarRepository lugarRepository;
    private final PersonaClient personaClient;
    
    @Override
    public ReservaLugarResponse crearReserva(ReservaLugarRequest request) {
        // 1. Validación inicial usando per_id
        if (request.getPer_id() == null || request.getLug_id() == null) {
            throw new RuntimeException("per_id y lug_id son obligatorios");
        }

        // 2. Validar cantidad
        if (request.getRes_lug_cantidad() == null || request.getRes_lug_cantidad() <= 0) {
            throw new RuntimeException("La cantidad de cupos debe ser mayor a 0");
        }

        // 3. Validar persona usando per_id
        PersonaResponse persona = personaClient.obtenerPersona(request.getPer_id());
        if (persona == null) {
            throw new RuntimeException("No existe persona con ID: " + request.getPer_id());
        }

        Lugar lugar = lugarRepository.findById(request.getLug_id())
                .orElseThrow(() -> new RuntimeException("Lugar no encontrado: " + request.getLug_id()));

        if (lugar.getLug_cupos_disponibles() < request.getRes_lug_cantidad()) {
            throw new RuntimeException("Cupos insuficientes en: " + lugar.getLug_nombre()
                    + ". Disponibles: " + lugar.getLug_cupos_disponibles()
                    + ", solicitados: " + request.getRes_lug_cantidad());
        }

        lugar.setLug_cupos_disponibles(lugar.getLug_cupos_disponibles() - request.getRes_lug_cantidad());
        lugarRepository.save(lugar);

        // 4. Guardar reserva usando per_id
        ReservaLugar reserva = ReservaLugar.builder()
                .per_id(request.getPer_id())
                .lug_id(request.getLug_id())
                .res_lug_cantidad(request.getRes_lug_cantidad())
                .res_lug_estado(EstadoReserva.PENDIENTE)
                .res_lug_fecha_creacion(LocalDateTime.now())
                .build();

        ReservaLugar saved = reservaRepository.save(reserva);

        return ReservaLugarResponse.builder()
                .res_lug_id(saved.getRes_lug_id())
                .per_id(saved.getPer_id())
                .lug_id(saved.getLug_id())
                .res_lug_cantidad(saved.getRes_lug_cantidad())
                .res_lug_estado(saved.getRes_lug_estado())
                .res_lug_fecha_creacion(saved.getRes_lug_fecha_creacion())
                .persona(persona)
                .mensaje("Reserva realizada con éxito")
                .build();
    }

    @Override
    public ReservaLugarResponse buscarPorId(Long id) {
        ReservaLugar reserva = reservaRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Reserva no encontrada"));

        PersonaResponse persona = personaClient.obtenerPersona(reserva.getPer_id());

        return ReservaLugarResponse.builder()
            .res_lug_id(reserva.getRes_lug_id())
            .per_id(reserva.getPer_id())
            .lug_id(reserva.getLug_id())
            .res_lug_cantidad(reserva.getRes_lug_cantidad())
            .res_lug_estado(reserva.getRes_lug_estado())
            .res_lug_fecha_creacion(reserva.getRes_lug_fecha_creacion())
            .persona(persona)
            .build();
    }

    @Override
public List<ReservaLugarResponse> listarTodas() {
    return reservaRepository.findAll().stream()
        .map(reserva -> ReservaLugarResponse.builder()
            .res_lug_id(reserva.getRes_lug_id())
            .per_id(reserva.getPer_id())
            .lug_id(reserva.getLug_id())
            .res_lug_cantidad(reserva.getRes_lug_cantidad())
            .res_lug_estado(reserva.getRes_lug_estado())
            .res_lug_fecha_creacion(reserva.getRes_lug_fecha_creacion())
            .persona(personaClient.obtenerPersona(reserva.getPer_id()))
            .build())
        .collect(Collectors.toList());
}

    @Override
    public ReservaLugarDTO obtenerReservaConPersona(Long id) {
        ReservaLugar reserva = reservaRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Reserva no encontrada"));

        return ReservaLugarDTO.builder()
            .res_lug_id(reserva.getRes_lug_id())
            .per_id(reserva.getPer_id())
            .persona(personaClient.obtenerPersona(reserva.getPer_id()))
            .build();
    }
}