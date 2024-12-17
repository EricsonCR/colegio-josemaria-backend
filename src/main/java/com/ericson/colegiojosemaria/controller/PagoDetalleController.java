package com.ericson.colegiojosemaria.controller;

import com.ericson.colegiojosemaria.service.PagoDetalleService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@CrossOrigin(value = "http://localhost:4200")
@RestController
@RequestMapping("/api/pago_detalle")
@RequiredArgsConstructor
public class PagoDetalleController {
    private final PagoDetalleService pagoDetalleService;

    @GetMapping("/listar")
    public ResponseEntity<Map<String, Object>> listar() {
        return pagoDetalleService.listar();
    }

    @GetMapping("/listarPorIdPago/{id}")
    public ResponseEntity<Map<String, Object>> listarPorIdPago(@PathVariable long id) {
        return pagoDetalleService.listarPorIdPago(id);
    }
}
