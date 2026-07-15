package ec.edu.ucacue.smartgym.client;

import ec.edu.ucacue.smartgym.dto.PersonaResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(
    name = "gym-persona-api", 
    url = "http://localhost:3003", 
    configuration = CustomErrorDecoder.class,
    fallback = PersonaClientFallback.class
)
public interface PersonaClient {
    
    @GetMapping("/api/personas/{id}")
    PersonaResponse obtenerPersona(@PathVariable("id") Long id);
    
}