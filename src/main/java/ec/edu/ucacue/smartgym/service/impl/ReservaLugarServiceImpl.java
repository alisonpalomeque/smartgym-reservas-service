package ec.edu.ucacue.smartgym.service.impl;

import ec.edu.ucacue.smartgym.dto.ReservaLugarResponse;
import ec.edu.ucacue.smartgym.dto.ReservaLugarRequest;
import ec.edu.ucacue.smartgym.entity.*;
import ec.edu.ucacue.smartgym.enums.EstadoReserva;
import ec.edu.ucacue.smartgym.repository.*;
import ec.edu.ucacue.smartgym.service.ReservaLugarService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ReservaLugarServiceImpl implements ReservaLugarService {

    private static final Logger log = LoggerFactory.getLogger(ReservaLugarServiceImpl.class);

    private final ReservaLugarRepository reservaRepository;
    private final LugarRepository lugarRepository;

    // Inyección mediante constructor (Reemplaza a @Autowired en campos)
    public ReservaLugarServiceImpl(ReservaLugarRepository reservaRepository, LugarRepository lugarRepository) {
        this.reservaRepository = reservaRepository;
        this.lugarRepository = lugarRepository;
    }

    @Override
    public ReservaLugarResponse crearReserva(ReservaLugarRequest request) {
        log.info("Intentando crear reserva: usu_id={}, lug_id={}", request.getUsu_id(), request.getLug_id());

        if (request.getUsu_id() == null || request.getLug_id() == null) {
            throw new RuntimeException("usu_id y lug_id son obligatorios");
        }

        Lugar lugar = lugarRepository.findById(request.getLug_id())
                .orElseThrow(() -> new RuntimeException("Lugar no encontrado con id: " + request.getLug_id()));

        if (lugar.getLug_cupos_disponibles() <= 0) {
            throw new RuntimeException("No hay cupos disponibles en: " + lugar.getLug_nombre());
        }

        lugar.setLug_cupos_disponibles(lugar.getLug_cupos_disponibles() - 1);
        lugarRepository.save(lugar);

        ReservaLugar reserva = ReservaLugar.builder()
                .usu_id(request.getUsu_id())
                .res_lug_estado(EstadoReserva.PENDIENTE)
                .res_lug_fecha_creacion(LocalDateTime.now())
                .build();

        ReservaLugar saved = reservaRepository.save(reserva);

        return ReservaLugarResponse.builder()
                .res_lug_id(saved.getRes_lug_id())
                .usu_id(saved.getUsu_id())
                .res_lug_estado(saved.getRes_lug_estado())
                .res_lug_fecha_creacion(saved.getRes_lug_fecha_creacion())
                .mensaje("Reserva realizada con éxito")
                .build();
    }

    @Override
    public ReservaLugarResponse buscarPorId(Long id) {
        return reservaRepository.findById(id)
            .map(reserva -> ReservaLugarResponse.builder()
                .res_lug_id(reserva.getRes_lug_id())
                .usu_id(reserva.getUsu_id())
                .res_lug_estado(reserva.getRes_lug_estado())
                .res_lug_fecha_creacion(reserva.getRes_lug_fecha_creacion())
                .mensaje("Reserva encontrada")
                .build())
            .orElseThrow(() -> new RuntimeException("Reserva no encontrada con ID: " + id));
    }

    @Override
    public List<ReservaLugarResponse> listarTodas() {
        return reservaRepository.findAll().stream()
            .map(reserva -> ReservaLugarResponse.builder()
                .res_lug_id(reserva.getRes_lug_id())
                .usu_id(reserva.getUsu_id())
                .res_lug_estado(reserva.getRes_lug_estado())
                .res_lug_fecha_creacion(reserva.getRes_lug_fecha_creacion())
                .build())
            .collect(Collectors.toList());
    }
}