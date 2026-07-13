package ec.edu.ucacue.smartgym.dto;

import lombok.*;
import java.time.LocalDateTime;
import java.time.LocalTime;

@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
@Builder
public class HorarioResponse {
    private Long hor_id;
    private Long med_usu_id;
    private LocalTime hor_hora_inicio;
    private LocalTime hor_hora_fin;
    private Boolean hor_estado;
}