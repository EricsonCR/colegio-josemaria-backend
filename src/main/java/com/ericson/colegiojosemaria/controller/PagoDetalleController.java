package com.ericson.colegiojosemaria.controller;

import com.ericson.colegiojosemaria.service.PagoDetalleService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

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
