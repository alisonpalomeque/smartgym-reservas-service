package ec.edu.ucacue.smartgym.dto;

import lombok.Data;

@Data
public class ReservaLugarRequest {
    private Long per_id;
    private Long lug_id;
    private Integer res_lug_cantidad;
}   