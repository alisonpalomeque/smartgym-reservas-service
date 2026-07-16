package ec.edu.ucacue.smartgym.dto;

import lombok.Data;
import java.time.LocalDate;
import java.time.LocalTime;

@Data 
public class LugarRequest {
    private String lug_nombre;
    private String lug_descripcion;
    private Integer lug_capacidad;
    private Boolean lug_estado;
    private LocalDate lug_dia_disponibles;
    private LocalTime lug_hora_inicio;
    private LocalTime lug_hora_fin;
    private String lug_imagen_url;
    private Integer lug_cupos_disponibles; // Cambiado a 'disponibles' para que coincida
}