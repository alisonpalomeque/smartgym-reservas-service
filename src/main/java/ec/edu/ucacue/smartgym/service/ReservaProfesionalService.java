package ec.edu.ucacue.smartgym.service;


import ec.edu.ucacue.smartgym.dto.ReservaProfesionalRequest;
import ec.edu.ucacue.smartgym.dto.ReservaProfesionalResponse;

import java.util.List;

public interface ReservaProfesionalService {
    ReservaProfesionalResponse crearReserva(ReservaProfesionalRequest request);
    List<ReservaProfesionalResponse> listarPorUsuario(Long usu_id);
  
}