package ec.edu.ucacue.smartgym.dto;

import java.time.LocalDateTime;

import lombok.Data;

@Data
public class HorarioProfesionalRequest {
    private Long profesional_id;
    private LocalDateTime hor_fecha_inicio; 
    private LocalDateTime hor_fecha_fin;
    private String hor_estado;
    private int hor_duracion_cita;
    private String hor_dia_semana;
}