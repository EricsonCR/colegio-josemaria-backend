package com.ericson.colegiojosemaria.interfaces;

import com.ericson.colegiojosemaria.model.Apoderado;
import org.springframework.http.ResponseEntity;

import java.util.Map;

public interface IApoderado {
    ResponseEntity<Map<String, Object>> listar();

    ResponseEntity<Map<String, Object>> crear(Apoderado apoderado);

    ResponseEntity<Map<String, Object>> buscarPorId(long id);
}
