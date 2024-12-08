package com.ericson.colegiojosemaria.interfaces;

import com.ericson.colegiojosemaria.model.Estudiante;
import org.springframework.http.ResponseEntity;

import java.util.Map;

public interface IEstudiante {
    ResponseEntity<Map<String, Object>> listar();

    ResponseEntity<Map<String, Object>> crear(Estudiante estudiante);

    ResponseEntity<Map<String, Object>> buscarPorNumero(String numero);

    ResponseEntity<Map<String, Object>> buscarPorId(long id);
}
