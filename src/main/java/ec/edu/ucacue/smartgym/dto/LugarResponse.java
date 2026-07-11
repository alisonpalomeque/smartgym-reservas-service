package ec.edu.ucacue.smartgym.dto;

import lombok.*;

@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
@Builder
public class LugarResponse {
    private Long lug_id;
    private String lug_nombre;
    private Integer lug_cupos_disponibles;
    private String lug_ubicacion;
}