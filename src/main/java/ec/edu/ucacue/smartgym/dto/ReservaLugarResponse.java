package ec.edu.ucacue.smartgym.dto;

import ec.edu.ucacue.smartgym.enums.EstadoReserva;
import lombok.*;
import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor @AllArgsConstructor
public class ReservaLugarResponse {
    private Long res_lug_id;
    private Long per_id; // CAMBIADO: aquí estaba usu_id
    private Long lug_id;
    private EstadoReserva res_lug_estado;
    private LocalDateTime res_lug_fecha_creacion;
    private String mensaje;
    private PersonaResponse persona;
}