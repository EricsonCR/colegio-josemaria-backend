package com.ericson.colegiojosemaria.controller;

import com.ericson.colegiojosemaria.service.MatriculaDetalleService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@CrossOrigin(value = "http://localhost:4200")
@RestController
@RequestMapping("/api/matricula_detalle")
@RequiredArgsConstructor
public class MatriculaDetalleController {
    private final MatriculaDetalleService matriculaDetalleService;

    @GetMapping("/listarPorIdMatricula/{id}")
    public ResponseEntity<Map<String, Object>> listarPorIdMatricula(@PathVariable int id) {
        return matriculaDetalleService.listarPorIdMatricula(id);
    }
}
