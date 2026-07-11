package ec.edu.ucacue.smartgym.controller;

import ec.edu.ucacue.smartgym.dto.ReservaLugarRequest;
import ec.edu.ucacue.smartgym.service.ReservaLugarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ec.edu.ucacue.smartgym.dto.ReservaLugarResponse;
import java.util.List;

@RestController
@RequestMapping("/api/reservas-lugar")
public class ReservaLugarController {

    @Autowired
    private ReservaLugarService service;

    @GetMapping
    public ResponseEntity<List<ReservaLugarResponse>> listarTodas() {
        return ResponseEntity.ok(service.listarTodas());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ReservaLugarResponse> buscarPorId(@PathVariable Long id) {
        return ResponseEntity.ok(service.buscarPorId(id));
    }

    @PostMapping
    public ResponseEntity<ReservaLugarResponse> crearReserva(@RequestBody ReservaLugarRequest request) {
        return ResponseEntity.ok(service.crearReserva(request));
    }
}