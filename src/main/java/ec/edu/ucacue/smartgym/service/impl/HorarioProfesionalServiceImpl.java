package ec.edu.ucacue.smartgym.service.impl;

import ec.edu.ucacue.smartgym.dto.HorarioResponse;
import ec.edu.ucacue.smartgym.repository.HorarioProfesionalRepository;
import ec.edu.ucacue.smartgym.service.HorarioProfesionalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class HorarioProfesionalServiceImpl implements HorarioProfesionalService {
    @Autowired
    private HorarioProfesionalRepository repository;

    @Override
    public List<HorarioResponse> obtenerPorProfesional(Long id) {
        // Aquí iría tu lógica, por ahora retorna vacío para que no falle al compilar
        return List.of(); 
    }
}