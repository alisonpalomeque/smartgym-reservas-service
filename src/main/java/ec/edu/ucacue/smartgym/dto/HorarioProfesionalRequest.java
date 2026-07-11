package ec.edu.ucacue.smartgym.dto;

import lombok.Data;
import java.time.LocalDateTime;

@Data
public class HorarioProfesionalRequest {
    private Long profesional_id;
    private LocalDateTime hor_fecha_inicio;
    private LocalDateTime hor_fecha_fin;
    private String hor_estado;
}