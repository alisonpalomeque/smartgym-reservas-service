package ec.edu.ucacue.smartgym.dto;

import lombok.*;
import java.time.LocalDateTime;

@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
@Builder
public class HorarioResponse {
    private Long hor_id;
    private Long profesional_id;
    private String profesional_nombre;
    private LocalDateTime hor_fecha_inicio;
    private LocalDateTime hor_fecha_fin;
    private String hor_estado;
}