package ec.edu.ucacue.smartgym.dto;

import ec.edu.ucacue.smartgym.enums.EstadoReserva;
import lombok.Data;
import java.time.LocalDateTime;

@Data
public class ReservaLugarDTO {
    private Long res_lug_id;
    private Long usu_id;
    private EstadoReserva res_lug_estado;
    private LocalDateTime res_lug_fecha_creacion;
    private PersonaResponse persona; 
}