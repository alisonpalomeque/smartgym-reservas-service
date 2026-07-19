package ec.edu.ucacue.smartgym.entity;

import ec.edu.ucacue.smartgym.enums.EstadoReserva;
import ec.edu.ucacue.smartgym.enums.TipoReserva; 

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDate;
import java.time.LocalTime;

@Entity
@Table(name = "tb_reserva_profesional")
@Getter 
@Setter
@NoArgsConstructor 
@AllArgsConstructor
@Builder
public class ReservaProfesional {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "res_pro_id")
    private Long resProId;

    private Long profesional_id;
    private String profesional_nombre;
    private Long usu_id;
    private LocalDate res_fecha;
    private LocalTime res_hora_inicio;
    private LocalTime res_hora_fin;

    @Enumerated(EnumType.STRING)
    private TipoReserva res_tipo;

    @Enumerated(EnumType.STRING)
    private EstadoReserva res_estado;

    private String res_comentario;
}