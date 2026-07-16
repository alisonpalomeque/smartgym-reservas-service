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
@Builder(toBuilder = true)
public class Lugar {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "lug_id")
    private Long lug_id;

    @Column(name = "lug_imagen_url")
    private String lug_imagen_url;

    @Column(name = "lug_nombre")
    private String lug_nombre;

    @Column(name = "lug_descripcion")
    private String lug_descripcion;

    @Column(name = "lug_capacidad")
    private Integer lug_capacidad;

    @Column(name = "lug_estado")
    private Boolean lug_estado;

    @Column(name = "lug_dia_disponibles")
    private LocalDate lug_dia_disponibles;

    @Column(name = "lug_hora_inicio")
    private LocalTime lug_hora_inicio;

    @Column(name = "lug_hora_fin")
    private LocalTime lug_hora_fin;

    @Column(name = "lug_cupos_disponibles")
    private Integer lug_cupos_disponibles;
}