package ec.edu.ucacue.smartgym.service;

import ec.edu.ucacue.smartgym.dto.*;
import java.util.List;

public interface ReservaLugarService {
   ReservaLugarResponse crearReserva(ReservaLugarRequest request);
    ReservaLugarResponse buscarPorId(Long id);
    List<ReservaLugarResponse> listarTodas();
    ReservaLugarDTO obtenerReservaConPersona(Long id);
}