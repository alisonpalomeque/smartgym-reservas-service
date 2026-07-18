package ec.edu.ucacue.smartgym.dto;

import lombok.*;
import java.time.LocalDateTime;
import java.time.LocalTime;

import ec.edu.ucacue.smartgym.enums.EstadoReserva;
import ec.edu.ucacue.smartgym.enums.TipoReserva;

@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
@Builder
public class ReservaProfesionalResponse {
    private Long res_prof_id;
    private Long profesional_id;
    private Long usu_id;
    private String profesional_nombre;
    private LocalDateTime fecha_reserva;
    private LocalTime res_hora_inicio;
    private LocalTime res_hora_fin;
    private TipoReserva res_tipo;
    private EstadoReserva res_estado;
    private String res_comentario;
    private String mensaje;
}