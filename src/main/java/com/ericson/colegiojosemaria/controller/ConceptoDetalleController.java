package com.ericson.colegiojosemaria.controller;

import com.ericson.colegiojosemaria.model.ConceptoDetalle;
import com.ericson.colegiojosemaria.service.ConceptoDetalleService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@CrossOrigin(value = "http://localhost:4200")
@RestController
@RequestMapping("/api/concepto-detalle")
@RequiredArgsConstructor
public class ConceptoDetalleController {
    private final ConceptoDetalleService conceptoDetalleService;

    @GetMapping("/listar")
    public ResponseEntity<Map<String, Object>> listar() {
        return conceptoDetalleService.listar();
    }

    @PostMapping("/crear")
    public ResponseEntity<Map<String, Object>> crear(@RequestBody ConceptoDetalle conceptoDetalle) {
        return conceptoDetalleService.crear(conceptoDetalle);
    }
}
