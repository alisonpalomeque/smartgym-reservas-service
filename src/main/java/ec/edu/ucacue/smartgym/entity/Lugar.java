package ec.edu.ucacue.smartgym.entity;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalTime;
import java.time.LocalDate;

@Entity
@Table(name = "tb_lugar")
@Getter @Setter
@NoArgsConstructor 
@AllArgsConstructor
@Builder(toBuilder = true) // <--- Agrega esto
public class Lugar {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long lug_id;

    private String lug_nombre;
    private String lug_descripcion;
    private Integer lug_capacidad;
    private Boolean lug_estado;
    private LocalDate lug_dia_disponibles;
    private LocalTime lug_hora_inicio;
    private LocalTime lug_hora_fin;
    private Integer lug_cupos_disponibles;
}
