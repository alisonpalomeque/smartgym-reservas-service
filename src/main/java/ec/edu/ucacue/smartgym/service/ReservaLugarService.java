package ec.edu.ucacue.smartgym.service;

import ec.edu.ucacue.smartgym.dto.ReservaLugarRequest;
import ec.edu.ucacue.smartgym.dto.ReservaLugarResponse;
import java.util.List;

public interface ReservaLugarService {
    ReservaLugarResponse crearReserva(ReservaLugarRequest request);
    ReservaLugarResponse buscarPorId(Long id);
    List<ReservaLugarResponse> listarTodas();
}