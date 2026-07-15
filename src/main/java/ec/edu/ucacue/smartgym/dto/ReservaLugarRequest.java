package ec.edu.ucacue.smartgym.dto;

import lombok.Data;
import java.time.LocalDate;

@Data
public class ReservaLugarRequest {
    private Long per_id; // Cambiado de usu_id a per_id
    private Long lug_id;
    private LocalDate res_fecha; // Asegúrate de incluir la fecha si la necesitas
}