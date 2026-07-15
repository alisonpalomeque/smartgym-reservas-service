package ec.edu.ucacue.smartgym.client;

import feign.Response;
import feign.codec.ErrorDecoder;

public class CustomErrorDecoder implements ErrorDecoder {

    @Override
    public Exception decode(String methodKey, Response response) {
        // Aquí capturas el error cuando el servicio 3001 responde
        if (response.status() == 404) {
            return new RuntimeException("El servicio de personas no encontró al usuario (404)");
        }
        if (response.status() >= 500) {
            return new RuntimeException("Error en el servidor de personas (500)");
        }
        return new Exception("Error de comunicación con servicio de personas: " + response.reason());
    }
}