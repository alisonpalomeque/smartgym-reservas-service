package ec.edu.ucacue.smartgym.controller;

import ec.edu.ucacue.smartgym.dto.ReservaLugarRequest;
import ec.edu.ucacue.smartgym.service.ReservaLugarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ec.edu.ucacue.smartgym.dto.ReservaLugarResponse;
import java.util.List;
import org.springframework.http.HttpStatus;

@RestController
@RequestMapping("/api/reservas-lugar")
public class ReservaLugarController {

    @Autowired
    private ReservaLugarService service;

    @PostMapping
    public ResponseEntity<ReservaLugarResponse> crearReserva(@RequestBody ReservaLugarRequest request) {
       
        return ResponseEntity.ok(service.crearReserva(request));

    }
   
@GetMapping("/{id}")
public ResponseEntity<ReservaLugarResponse> obtenerPorId(@PathVariable Long id) {
    return ResponseEntity.ok(service.buscarPorId(id));
}

    @GetMapping
public ResponseEntity<List<ReservaLugarResponse>> listarReservas() {
    return ResponseEntity.ok(service.listarTodas());
}

}