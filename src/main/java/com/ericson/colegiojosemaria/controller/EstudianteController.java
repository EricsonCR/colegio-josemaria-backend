package com.ericson.colegiojosemaria.controller;

import com.ericson.colegiojosemaria.model.Estudiante;
import com.ericson.colegiojosemaria.service.EstudianteService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@CrossOrigin(value = "http://localhost:4200")
@RestController
@RequestMapping("/api/estudiante")
@RequiredArgsConstructor
public class EstudianteController {
    private final EstudianteService estudianteService;

    @GetMapping("/listar")
    public ResponseEntity<Map<String, Object>> listar() {
        return estudianteService.listar();
    }

    @GetMapping("/buscarPorNumero/{numero}")
    public ResponseEntity<Map<String, Object>> buscarPorNumero(@PathVariable String numero) {
        return estudianteService.buscarPorNumero(numero);
    }

    @GetMapping("/buscarPorId/{id}")
    public ResponseEntity<Map<String, Object>> buscarPorId(@PathVariable long id) {
        return estudianteService.buscarPorId(id);
    }

    @PostMapping("/crear")
    public ResponseEntity<Map<String, Object>> crear(@RequestBody Estudiante estudiante) {
        return estudianteService.crear(estudiante);
    }
}
