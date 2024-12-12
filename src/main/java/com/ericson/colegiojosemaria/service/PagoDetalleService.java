package com.ericson.colegiojosemaria.service;

import com.ericson.colegiojosemaria.interfaces.IPagoDetalle;
import com.ericson.colegiojosemaria.model.PagoDetalle;
import com.ericson.colegiojosemaria.repository.PagoDetalleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class PagoDetalleService implements IPagoDetalle {
    private final PagoDetalleRepository pagoDetalleRepository;

    @Override
    public ResponseEntity<Map<String, Object>> listar() {
        HashMap<String, Object> response = new HashMap<>();
        List<PagoDetalle> lista = new ArrayList<>();
        try {
            lista = pagoDetalleRepository.findAll();
            response.put("data", lista);
            response.put("message", "Lista de pagos detalle");
            response.put("status", HttpStatus.OK.value());
        } catch (Exception e) {
            response.put("data", lista);
            response.put("message", e.getMessage());
            response.put("status", HttpStatus.INTERNAL_SERVER_ERROR.value());
        }
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @Override
    public ResponseEntity<Map<String, Object>> listarPorIdPago(long id) {
        HashMap<String, Object> response = new HashMap<>();
        List<PagoDetalle> lista = new ArrayList<>();
        try {
            lista = pagoDetalleRepository.listarPorIdPago(id);
            response.put("data", lista);
            response.put("message", "Lista de pagos detalle por id");
            response.put("status", HttpStatus.OK.value());
        } catch (Exception e) {
            response.put("data", lista);
            response.put("message", e.getMessage());
            response.put("status", HttpStatus.INTERNAL_SERVER_ERROR.value());
        }
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }
}
