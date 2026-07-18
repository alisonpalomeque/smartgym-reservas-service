package ec.edu.ucacue.smartgym.dto;

import lombok.*;
import java.time.LocalDateTime;

@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
@Builder
public class ReservaProfesionalResponse {
    private Long res_prof_id;
    private Long profesional_id;
    private Long usu_id;
    private String profesional_nombre;
    private LocalDateTime fecha_reserva;
    private String mensaje;
}