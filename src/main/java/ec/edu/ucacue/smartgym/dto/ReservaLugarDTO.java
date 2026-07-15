package ec.edu.ucacue.smartgym.dto;

import lombok.*;

@Data
@Builder // <--- ESTA ES LA QUE FALTA
@NoArgsConstructor // <--- IMPORTANTE para que el builder funcione
@AllArgsConstructor // <--- IMPORTANTE para que el builder funcione
public class ReservaLugarDTO {
    private Long res_lug_id;
    private Long per_id;
    private PersonaResponse persona;
}