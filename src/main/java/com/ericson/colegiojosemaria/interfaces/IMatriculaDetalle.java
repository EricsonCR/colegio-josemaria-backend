package com.ericson.colegiojosemaria.interfaces;

import org.springframework.http.ResponseEntity;

import java.util.Map;

public interface IMatriculaDetalle {
    ResponseEntity<Map<String, Object>> listar();
    ResponseEntity<Map<String, Object>> listarPorIdMatricula(long id);

}
