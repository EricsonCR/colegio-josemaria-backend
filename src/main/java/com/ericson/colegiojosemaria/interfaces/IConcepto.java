package com.ericson.colegiojosemaria.interfaces;

import com.ericson.colegiojosemaria.model.Concepto;
import org.springframework.http.ResponseEntity;

import java.util.Map;

public interface IConcepto {
    ResponseEntity<Map<String, Object>> listar();

    ResponseEntity<Map<String, Object>> crear(Concepto concepto);
}
