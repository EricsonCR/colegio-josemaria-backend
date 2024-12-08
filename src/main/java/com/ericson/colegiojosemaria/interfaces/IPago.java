package com.ericson.colegiojosemaria.interfaces;

import com.ericson.colegiojosemaria.model.Pago;
import org.springframework.http.ResponseEntity;

import java.util.Map;

public interface IPago {
    ResponseEntity<Map<String,Object>> listar();
    ResponseEntity<Map<String, Object>> crear(Pago pago);
}
