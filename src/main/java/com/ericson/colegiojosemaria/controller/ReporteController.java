package com.ericson.colegiojosemaria.controller;

import com.ericson.colegiojosemaria.service.ReporteService;
import com.itextpdf.text.DocumentException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/api/reporte")
@RequiredArgsConstructor
public class ReporteController {
    private final ReporteService reporteService;

    @GetMapping("/pagoPorId/{id}")
    public ResponseEntity<Map<String, Object>> pagoPorId(@PathVariable long id) throws DocumentException {
        return reporteService.reportePagoId(id);
    }
}
