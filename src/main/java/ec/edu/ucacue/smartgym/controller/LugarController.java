package ec.edu.ucacue.smartgym.controller;

import ec.edu.ucacue.smartgym.dto.LugarRequest;
import ec.edu.ucacue.smartgym.dto.LugarResponse;
import ec.edu.ucacue.smartgym.service.LugarService; // Solo un import
import jakarta.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/reservas/lugares")
public class LugarController {

    @Autowired
    private LugarService service;

    @GetMapping
    public ResponseEntity<List<LugarResponse>> listarTodos() {
        return ResponseEntity.ok(service.listarTodos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<LugarResponse> obtenerPorId(@PathVariable Long id) {
        return ResponseEntity.ok(service.obtenerPorId(id));
    }

    @PostMapping
public ResponseEntity<LugarResponse> crear(@RequestBody LugarRequest request) {
    System.out.println("JSON RECIBIDO: " + request.toString()); // Verás en la consola si los campos llegan vacíos
    return ResponseEntity.status(org.springframework.http.HttpStatus.CREATED)
                         .body(service.crearLugar(request));
}

}