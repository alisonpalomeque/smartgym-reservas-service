package ec.edu.ucacue.smartgym.controller;

import ec.edu.ucacue.smartgym.dto.ReservaProfesionalRequest;
import ec.edu.ucacue.smartgym.dto.ReservaProfesionalResponse;
import ec.edu.ucacue.smartgym.service.ReservaProfesionalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/reservas/profesional")
public class ReservaProfesionalController {

    @Autowired
    private ReservaProfesionalService service;

    @PostMapping
    public ResponseEntity<ReservaProfesionalResponse> crear(@RequestBody ReservaProfesionalRequest request) {
        return ResponseEntity.ok(service.crearReserva(request));
    }

    @GetMapping("/usuario/{usu_id}")
    public ResponseEntity<List<ReservaProfesionalResponse>> listarPorUsuario(@PathVariable Long usu_id) {
        return ResponseEntity.ok(service.listarPorUsuario(usu_id));
    }


}
