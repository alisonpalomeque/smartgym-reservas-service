package ec.edu.ucacue.smartgym.entity;

import ec.edu.ucacue.smartgym.enums.EstadoReserva;
import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;


@Entity
@Table(name = "tb_reserva_lugar")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class ReservaLugar {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "res_lug_id")
    private Long res_lug_id;

    @Column(name = "usu_id")
    private Long usu_id;

    @Column(name = "lug_id")
    private Long lug_id;

    @Enumerated(EnumType.STRING)
    @Column(name = "res_lug_estado")
    private EstadoReserva res_lug_estado;

    @Column(name = "res_lug_fecha_creacion")
    private LocalDateTime res_lug_fecha_creacion;
}