package ec.edu.ucacue.smartgym.controller;

import ec.edu.ucacue.smartgym.dto.HorarioResponse;
import ec.edu.ucacue.smartgym.service.HorarioProfesionalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/horarios")
public class HorarioProfesionalController {

    @Autowired
    private HorarioProfesionalService service;

    @GetMapping("/profesional/{id}")
    public ResponseEntity<List<HorarioResponse>> obtenerPorProfesional(@PathVariable Long id) {
        return ResponseEntity.ok(service.obtenerPorProfesional(id));
    }
}