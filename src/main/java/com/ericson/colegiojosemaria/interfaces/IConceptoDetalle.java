package com.ericson.colegiojosemaria.interfaces;

import com.ericson.colegiojosemaria.model.ConceptoDetalle;
import org.springframework.http.ResponseEntity;

import java.util.Map;

public interface IConceptoDetalle {
    ResponseEntity<Map<String, Object>> listar();

    ResponseEntity<Map<String, Object>> crear(ConceptoDetalle conceptoDetalle);
}
