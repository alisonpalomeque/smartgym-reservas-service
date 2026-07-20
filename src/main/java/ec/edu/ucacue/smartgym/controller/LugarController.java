package ec.edu.ucacue.smartgym.controller;

import ec.edu.ucacue.smartgym.dto.LugarRequest;
import ec.edu.ucacue.smartgym.dto.LugarResponse;
import ec.edu.ucacue.smartgym.service.LugarService; // Solo un import
import jakarta.validation.Valid;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import org.slf4j.Logger;

@RestController
@RequestMapping("/api/reservas/lugares")
public class LugarController {

    private static final Logger log = LoggerFactory.getLogger(LugarController.class);

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
public ResponseEntity<LugarResponse> crear(@Valid @RequestBody LugarRequest request) {
    return ResponseEntity.status(HttpStatus.CREATED)
                         .body(service.crearLugar(request));
                         
}

    @PutMapping("/{id}")
    public ResponseEntity<LugarResponse> actualizar(@PathVariable Long id, @Valid @RequestBody LugarRequest request) {
        return ResponseEntity.ok(service.actualizarLugar(id, request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        service.eliminarLugar(id);
        return ResponseEntity.noContent().build();
    }

}