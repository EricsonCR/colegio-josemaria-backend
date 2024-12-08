package com.ericson.colegiojosemaria.controller;

import com.ericson.colegiojosemaria.model.Apoderado;
import com.ericson.colegiojosemaria.service.ApoderadoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@CrossOrigin(value = "http://localhost:4200")
@RestController
@RequestMapping("/api/apoderado")
@RequiredArgsConstructor
public class ApoderadoController {
    private final ApoderadoService apoderadoService;

    @GetMapping("/listar")
    public ResponseEntity<Map<String, Object>> listar() {
        return apoderadoService.listar();
    }

    @GetMapping("/buscarPorId/{id}")
    public ResponseEntity<Map<String, Object>> buscarPorId(@PathVariable long id) {
        return apoderadoService.buscarPorId(id);
    }

    @PostMapping("/crear")
    public ResponseEntity<Map<String, Object>> crear(@RequestBody Apoderado apoderado) {
        return apoderadoService.crear(apoderado);
    }
}
