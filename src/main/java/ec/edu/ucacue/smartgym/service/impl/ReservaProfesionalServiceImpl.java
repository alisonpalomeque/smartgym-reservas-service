package ec.edu.ucacue.smartgym.service.impl;

import ec.edu.ucacue.smartgym.dto.PersonaResponse;
import ec.edu.ucacue.smartgym.dto.ReservaProfesionalRequest;
import ec.edu.ucacue.smartgym.dto.ReservaProfesionalResponse;
import ec.edu.ucacue.smartgym.entity.ReservaProfesional;
import ec.edu.ucacue.smartgym.enums.EstadoReserva;
import ec.edu.ucacue.smartgym.repository.ReservaProfesionalRepository;
import ec.edu.ucacue.smartgym.service.ReservaProfesionalService;
import ec.edu.ucacue.smartgym.client.PersonaClient;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ReservaProfesionalServiceImpl implements ReservaProfesionalService {

    private final ReservaProfesionalRepository repository;
    private final PersonaClient personaClient;

    @Override
    public ReservaProfesionalResponse crearReserva(ReservaProfesionalRequest request) {
        // 1. VALIDACIÓN: Fecha no puede ser pasada
        if (request.getFecha_reserva().isBefore(LocalDateTime.now())) {
            throw new RuntimeException("Error: No se puede reservar en una fecha pasada.");
        }

        // 2. VALIDACIÓN: Evitar que el profesional tenga dos reservas el mismo día
        boolean ocupado = repository.existsByProfesional_idAndRes_fecha(
                request.getProfesional_id(), 
                request.getFecha_reserva().toLocalDate()
        );
        
        if (ocupado) {
            throw new RuntimeException("Error: El profesional ya tiene una reserva asignada para este día.");
        }

        // 3. Creación y guardado
        ReservaProfesional reserva = new ReservaProfesional();
        reserva.setProfesional_id(request.getProfesional_id());
        reserva.setUsu_id(request.getUsuario_id());
        reserva.setRes_fecha(request.getFecha_reserva().toLocalDate());
        reserva.setRes_comentario(request.getRes_comentario());
        reserva.setRes_estado(EstadoReserva.PENDIENTE);
        reserva.setRes_hora_inicio(request.getFecha_reserva().toLocalTime());
        reserva.setRes_hora_fin(request.getRes_hora_fin());
        reserva.setRes_tipo(request.getRes_tipo());

        ReservaProfesional guardada = repository.save(reserva);

        return ReservaProfesionalResponse.builder()
                .res_prof_id(guardada.getResProId())
                .usu_id(guardada.getUsu_id())
                .fecha_reserva(guardada.getRes_fecha().atStartOfDay())
                .mensaje("Reserva creada con éxito")
                .build();
    }

    @Override
    public List<ReservaProfesionalResponse> listarPorUsuario(Long usu_id) {
        return repository.findByUsu_id(usu_id).stream()
                .map(r -> {
                    String nombre = "Desconocido";
                    try {
                        PersonaResponse persona = personaClient.obtenerPersona(r.getProfesional_id());
                        if (persona != null && persona.getPer_nombres() != null) {
                            nombre = persona.getPer_nombres() + " " + (persona.getPer_apellidos() != null ? persona.getPer_apellidos() : "");
                        }
                    } catch (Exception e) {
                        // Servicio no disponible, nombre queda "Desconocido"
                    }
                    return ReservaProfesionalResponse.builder()
                            .res_prof_id(r.getResProId())
                            .profesional_id(r.getProfesional_id())
                            .usu_id(r.getUsu_id())
                            .profesional_nombre(nombre)
                            .fecha_reserva(r.getRes_fecha().atStartOfDay())
                            .res_hora_inicio(r.getRes_hora_inicio())
                            .res_hora_fin(r.getRes_hora_fin())
                            .res_tipo(r.getRes_tipo())
                            .res_estado(r.getRes_estado())
                            .res_comentario(r.getRes_comentario())
                            .mensaje("Reserva encontrada")
                            .build();
                })
                .collect(Collectors.toList());
    }
}