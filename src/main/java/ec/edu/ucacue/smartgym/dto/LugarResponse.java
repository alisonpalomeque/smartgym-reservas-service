package ec.edu.ucacue.smartgym.dto;

import lombok.*;
import java.time.LocalDate;
import java.time.LocalTime;

@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
@Builder
public class LugarResponse {
    private Long lug_id;
    private String lug_nombre;
    private String lug_descripcion;
    private Integer lug_capacidad;
    private Boolean lug_estado;
    private LocalDate lug_dia_disponibles;
    private LocalTime lug_hora_inicio;
    private LocalTime lug_hora_fin;
    private Integer lug_cupos_disponibles;
    private String lug_imagen_url;
}