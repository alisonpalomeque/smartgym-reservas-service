package ec.edu.ucacue.smartgym.dto;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
@Data
@Builder 
@NoArgsConstructor
@AllArgsConstructor
public class PersonaResponse {
    private Long per_id;
    private String per_nombres;
    private String per_apellidos;
}