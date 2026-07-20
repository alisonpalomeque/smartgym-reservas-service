package ec.edu.ucacue.smartgym.service.impl;


import ec.edu.ucacue.smartgym.dto.LugarRequest;
import ec.edu.ucacue.smartgym.dto.LugarResponse;
import ec.edu.ucacue.smartgym.entity.Lugar;
import ec.edu.ucacue.smartgym.repository.LugarRepository;
import ec.edu.ucacue.smartgym.service.LugarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class LugarServiceImpl implements LugarService {

    @Autowired
    private LugarRepository lugarRepository;

    @Override
    public List<LugarResponse> listarTodos() {
       
        return lugarRepository.findAll().stream()
                .map(this::mapearADto)
                .collect(Collectors.toList());
    }

    @Override
    public LugarResponse obtenerPorId(Long id) {
        Lugar entidad = lugarRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Lugar no encontrado con ID: " + id));
        
        return mapearADto(entidad);
    }
        @Override
    public LugarResponse crearLugar(LugarRequest request) {
        // Aquí es donde ocurre el fallo: debes asignar cada campo del 'request' a la 'entidad'
        Lugar lugar = Lugar.builder()
                .lug_nombre(request.getLug_nombre())
                .lug_descripcion(request.getLug_descripcion())
                .lug_capacidad(request.getLug_capacidad())
                .lug_estado(request.getLug_estado())
                .lug_dia_disponibles(request.getLug_dia_disponibles())
                .lug_hora_inicio(request.getLug_hora_inicio())
                .lug_hora_fin(request.getLug_hora_fin())
                .lug_cupos_disponibles(request.getLug_cupos_disponibles()) // ¡Este faltaba!
                .lug_imagen_url(request.getLug_imagen_url()) // Asegúrate de que este campo exista en la entidad
                .build();

        Lugar guardado = lugarRepository.save(lugar);
        return mapearADto(guardado);
    }

    @Override
    public LugarResponse actualizarLugar(Long id, LugarRequest request) {
        Lugar existente = lugarRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Lugar no encontrado con ID: " + id));

        Lugar actualizado = existente.toBuilder()
                .lug_nombre(request.getLug_nombre())
                .lug_descripcion(request.getLug_descripcion())
                .lug_capacidad(request.getLug_capacidad())
                .lug_estado(request.getLug_estado())
                .lug_dia_disponibles(request.getLug_dia_disponibles())
                .lug_hora_inicio(request.getLug_hora_inicio())
                .lug_hora_fin(request.getLug_hora_fin())
                .lug_cupos_disponibles(request.getLug_cupos_disponibles())
                .lug_imagen_url(request.getLug_imagen_url())
                .build();

        Lugar guardado = lugarRepository.save(actualizado);
        return mapearADto(guardado);
    }

    @Override
    public void eliminarLugar(Long id) {
        if (!lugarRepository.existsById(id)) {
            throw new RuntimeException("Lugar no encontrado con ID: " + id);
        }
        lugarRepository.deleteById(id);
    }

    private LugarResponse mapearADto(Lugar lugar) {
    return LugarResponse.builder()
            .lug_id(lugar.getLug_id())
            .lug_nombre(lugar.getLug_nombre())
            // Asegúrate de que el nombre aquí coincida exactamente con el de la entidad
            .lug_descripcion(lugar.getLug_descripcion()) 
            .lug_capacidad(lugar.getLug_capacidad())
            .lug_estado(lugar.getLug_estado())
            .lug_dia_disponibles(lugar.getLug_dia_disponibles())
            .lug_hora_inicio(lugar.getLug_hora_inicio())
            .lug_hora_fin(lugar.getLug_hora_fin())
            .lug_cupos_disponibles(lugar.getLug_cupos_disponibles())
            .lug_imagen_url(lugar.getLug_imagen_url()) // Asegúrate de que este campo exista en la entidad
            .build();
}

}