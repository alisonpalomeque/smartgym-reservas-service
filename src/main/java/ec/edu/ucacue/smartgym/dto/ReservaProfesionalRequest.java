package ec.edu.ucacue.smartgym.dto;

import java.time.LocalDateTime;
import java.time.LocalTime;

import ec.edu.ucacue.smartgym.enums.TipoReserva;
import lombok.Data;
@Data
public class ReservaProfesionalRequest {
    private Long profesional_id;
    private Long entrenador_id;
    private Long usuario_id;
    private LocalDateTime fecha_reserva;
    private String res_comentario; 
    private LocalTime res_hora_inicio;
    private LocalTime res_hora_fin;
    private TipoReserva res_tipo;
}