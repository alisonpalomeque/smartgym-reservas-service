package ec.edu.ucacue.smartgym.client;

import ec.edu.ucacue.smartgym.dto.PersonaResponse;
import org.springframework.stereotype.Component;

@Component
public class PersonaClientFallback implements PersonaClient {
    @Override
    public PersonaResponse obtenerPersona(Long id) {
        return PersonaResponse.builder()
                .per_id(0L)
                .per_nombres("Usuario Temporal")
                .build();
    }
}