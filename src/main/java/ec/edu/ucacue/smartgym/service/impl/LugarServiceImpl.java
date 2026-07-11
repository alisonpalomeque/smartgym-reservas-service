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
    // 1. Convertir el DTO Request a Entidad
    Lugar lugar = new Lugar();
    lugar.setLug_nombre(request.getLug_nombre());
    lugar.setLug_cupos_disponibles(request.getLug_cupos_totales());
    // ... asigna el resto de campos que tengas en tu entidad ...

    // 2. Guardar en BD usando el repositorio
    Lugar guardado = lugarRepository.save(lugar);

    // 3. Convertir la entidad guardada a Response y retornar
    return mapearADto(guardado);
   }

    private LugarResponse mapearADto(Lugar lugar) {
        return LugarResponse.builder()
                .lug_id(lugar.getLug_id())
                .lug_nombre(lugar.getLug_nombre())
                .lug_cupos_disponibles(lugar.getLug_cupos_disponibles())
                .build();
    }
}