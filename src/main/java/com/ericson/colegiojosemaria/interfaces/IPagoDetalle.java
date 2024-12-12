package com.ericson.colegiojosemaria.interfaces;

import org.springframework.http.ResponseEntity;

import java.util.Map;

public interface IPagoDetalle {
    ResponseEntity<Map<String, Object>> listar();
    ResponseEntity<Map<String, Object>> listarPorIdPago(long id);
}
