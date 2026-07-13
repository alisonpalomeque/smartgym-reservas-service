package ec.edu.ucacue.smartgym.service.impl;

import ec.edu.ucacue.smartgym.dto.HorarioResponse;
import ec.edu.ucacue.smartgym.repository.HorarioProfesionalRepository;
import ec.edu.ucacue.smartgym.service.HorarioProfesionalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;
import ec.edu.ucacue.smartgym.dto.HorarioProfesionalRequest;
import ec.edu.ucacue.smartgym.entity.HorarioProfesional;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.LocalDate;



@Service
public class HorarioProfesionalServiceImpl implements HorarioProfesionalService {

    @Autowired
    private HorarioProfesionalRepository repository;

    @Override
    public List<HorarioResponse> obtenerPorProfesional(Long id) {
        return repository.findByMedUsuId(id).stream()
            .map(h -> {
                HorarioResponse dto = new HorarioResponse();
                dto.setHor_id(h.getHor_id());
                dto.setMed_usu_id(h.getMed_usu_id());
                dto.setHor_hora_inicio(h.getHor_hora_inicio());
                dto.setHor_hora_fin(h.getHor_hora_fin());
                dto.setHor_estado(h.getHor_estado());
                return dto;
            })
            .collect(Collectors.toList());
    }

@Override
public void guardar(HorarioProfesionalRequest request) {
    HorarioProfesional horario = new HorarioProfesional();
    
    // Asignación del ID del profesional
    horario.setMed_usu_id(request.getProfesional_id());
    
    // Aquí usamos los métodos que existen en tu DTO:
    if (request.getHor_fecha_inicio() != null) {
        horario.setHor_hora_inicio(request.getHor_fecha_inicio().toLocalTime());
    }
    
    if (request.getHor_fecha_fin() != null) {
        horario.setHor_hora_fin(request.getHor_fecha_fin().toLocalTime());
    }
    
    horario.setHor_estado(true);
    horario.setHor_fecha_creacion(java.time.LocalDateTime.now());
    
    repository.save(horario);
}
}