package com.ericson.colegiojosemaria.interfaces;

import com.ericson.colegiojosemaria.model.Matricula;
import org.springframework.http.ResponseEntity;

import java.util.Map;

public interface IMatricula {
    ResponseEntity<Map<String, Object>> listar();

    ResponseEntity<Map<String, Object>> buscarPorId(long id);

    ResponseEntity<Map<String, Object>> buscarPorIdEstudiante(long id);

    ResponseEntity<Map<String, Object>> crear(Matricula matricula);
}
