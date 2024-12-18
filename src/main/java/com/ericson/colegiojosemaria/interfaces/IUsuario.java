package com.ericson.colegiojosemaria.interfaces;

import com.ericson.colegiojosemaria.model.Usuario;
import org.springframework.http.ResponseEntity;

import java.util.Map;
import java.util.Optional;

public interface IUsuario {
    ResponseEntity<Map<String, Object>> listar();

    ResponseEntity<Map<String, Object>> buscarPorEmail(String email);

    ResponseEntity<Map<String, Object>> registrar(Usuario usuario);
}
