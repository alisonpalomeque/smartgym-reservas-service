package ec.edu.ucacue.smartgym.service;

import ec.edu.ucacue.smartgym.dto.HorarioResponse;
import java.util.List;

public interface HorarioProfesionalService {

    List<HorarioResponse> obtenerPorProfesional(Long profesionalId);
}