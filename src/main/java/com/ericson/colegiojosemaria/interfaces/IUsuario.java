package com.ericson.colegiojosemaria.interfaces;

import com.ericson.colegiojosemaria.model.Usuario;
import org.springframework.http.ResponseEntity;

import java.util.Map;

public interface IUsuario {
    ResponseEntity<Map<String, Object>> listar();

    ResponseEntity<Map<String, Object>> crear(Usuario usuario);
}
