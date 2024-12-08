package com.ericson.colegiojosemaria.controller;

import com.ericson.colegiojosemaria.model.Matricula;
import com.ericson.colegiojosemaria.service.MatriculaService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@CrossOrigin(value = "http://localhost:4200")
@RestController
@RequestMapping("/api/matricula")
@RequiredArgsConstructor
public class MatriculaController {
    private final MatriculaService matriculaService;

    @GetMapping("/listar")
    public ResponseEntity<Map<String, Object>> listar() {
        return matriculaService.listar();
    }

    @GetMapping("/buscarPorId/{id}")
    public ResponseEntity<Map<String, Object>> buscarPorId(@PathVariable long id) {
        return matriculaService.buscarPorId(id);
    }
    
    @GetMapping("/buscarPorIdEstudiante/{id}")
    public ResponseEntity<Map<String, Object>> buscarPorIdEstudiante(@PathVariable long id) {
        return matriculaService.buscarPorIdEstudiante(id);
    }

    @PostMapping("/crear")
    public ResponseEntity<Map<String, Object>> crear(@RequestBody Matricula matricula) {
        return matriculaService.crear(matricula);
    }
}
