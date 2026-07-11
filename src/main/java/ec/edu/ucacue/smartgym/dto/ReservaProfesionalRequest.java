package ec.edu.ucacue.smartgym.dto;

import lombok.Data;
import java.time.LocalDateTime;

@Data
public class ReservaProfesionalRequest {
    private Long usu_id;
    private Long profesional_id;
    private LocalDateTime fecha_reserva;
}