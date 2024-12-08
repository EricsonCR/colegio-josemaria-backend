package com.ericson.colegiojosemaria.controller;

import com.ericson.colegiojosemaria.model.Usuario;
import com.ericson.colegiojosemaria.service.UsuarioService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/usuario")
@RequiredArgsConstructor
public class UsuarioController {
    private final UsuarioService usuarioService;

    @GetMapping("/listar")
    public ResponseEntity<Map<String, Object>> listar() {
        return usuarioService.listar();
    }

    @PostMapping("/crear")
    public ResponseEntity<Map<String, Object>> crear(@RequestBody Usuario usuario) {
        return usuarioService.crear(usuario);
    }
}
