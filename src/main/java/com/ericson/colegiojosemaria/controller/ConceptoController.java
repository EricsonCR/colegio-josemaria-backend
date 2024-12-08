package com.ericson.colegiojosemaria.controller;

import com.ericson.colegiojosemaria.model.Concepto;
import com.ericson.colegiojosemaria.service.ConceptoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@CrossOrigin(value = "http://localhost:4200")
@RestController
@RequestMapping("/api/concepto")
@RequiredArgsConstructor
public class ConceptoController {
    private final ConceptoService conceptoService;

    @GetMapping("/listar")
    public ResponseEntity<Map<String, Object>> listar() {
        return conceptoService.listar();
    }

    @PostMapping("/crear")
    public ResponseEntity<Map<String, Object>> crear(@RequestBody Concepto concepto) {
        return conceptoService.crear(concepto);
    }
}
