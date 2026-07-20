package ec.edu.ucacue.smartgym.service;
import ec.edu.ucacue.smartgym.dto.LugarRequest;
import ec.edu.ucacue.smartgym.dto.LugarResponse;
import java.util.List;

public interface LugarService {

    // Método para obtener todos los lugares
    List<LugarResponse> listarTodos();

    // Método para obtener un lugar específico por su ID
    LugarResponse obtenerPorId(Long id);
    LugarResponse crearLugar(LugarRequest request);

    LugarResponse actualizarLugar(Long id, LugarRequest request);

    void eliminarLugar(Long id);
}