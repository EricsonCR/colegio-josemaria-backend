package com.ericson.colegiojosemaria.controller;

import com.ericson.colegiojosemaria.model.Pago;
import com.ericson.colegiojosemaria.service.PagoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@CrossOrigin(value = "http://localhost:4200")
@RestController
@RequestMapping("/api/pago")
@RequiredArgsConstructor
public class PagoController {
    private final PagoService pagoService;

    @GetMapping("/listar")
    public ResponseEntity<Map<String, Object>> listar() {
        return pagoService.listar();
    }

    @PostMapping("/crear")
    public ResponseEntity<Map<String, Object>> crearPago(@RequestBody Pago pago) {
        return pagoService.crear(pago);
    }

    @GetMapping("/buscarPorId/{id}")
    public ResponseEntity<Map<String, Object>> buscarPorId(@PathVariable Long id) {
        return pagoService.buscarPorId(id);
    }
}
