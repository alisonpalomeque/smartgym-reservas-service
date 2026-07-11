package ec.edu.ucacue.smartgym.service.impl;

import ec.edu.ucacue.smartgym.dto.ReservaProfesionalRequest;
import ec.edu.ucacue.smartgym.dto.ReservaProfesionalResponse;
import ec.edu.ucacue.smartgym.repository.ReservaProfesionalRepository;
import ec.edu.ucacue.smartgym.service.ReservaProfesionalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class ReservaProfesionalServiceImpl implements ReservaProfesionalService {
    @Autowired
    private ReservaProfesionalRepository repository;

    @Override
    public ReservaProfesionalResponse crearReserva(ReservaProfesionalRequest request) {
        return new ReservaProfesionalResponse(); // Pendiente lógica de guardado
    }

    @Override
    public List<ReservaProfesionalResponse> listarPorUsuario(Long usu_id) {
        return List.of();
    }
}