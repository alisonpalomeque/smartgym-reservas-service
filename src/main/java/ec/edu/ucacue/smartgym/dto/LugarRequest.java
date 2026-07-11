package ec.edu.ucacue.smartgym.dto;

import lombok.Data;

@Data
public class LugarRequest {
    private String lug_nombre;
    private Integer lug_cupos_totales;
    private String lug_ubicacion;
}