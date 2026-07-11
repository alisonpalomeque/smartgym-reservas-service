package ec.edu.ucacue.smartgym.entity;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;
import java.time.LocalTime;

@Entity
@Table(name = "tb_horario_profesional")
@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
@Builder
public class HorarioProfesional {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long hor_id;

    private Long med_usu_id;
    private String hor_dia_semana;
    private LocalTime hor_hora_inicio;
    private LocalTime hor_hora_fin;
    private Integer hor_duracion_cita;
    private Boolean hor_estado;
    private LocalDateTime hor_fecha_creacion;
    private LocalDateTime hor_fecha_actualizacion;
}