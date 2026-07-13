package ec.edu.ucacue.smartgym.dto;

import lombok.Data;
import java.time.LocalDateTime;
@Data
public class ReservaProfesionalRequest {
    private Long profesional_id;
    private Long usuario_id;
    private LocalDateTime fecha_reserva;
    private String res_comentario; 
}