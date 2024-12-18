package com.ericson.colegiojosemaria.controller;

import com.ericson.colegiojosemaria.model.Usuario;
import com.ericson.colegiojosemaria.service.UsuarioService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@CrossOrigin(value = "http://localhost:4200")
@RestController
@RequestMapping("/api/usuario")
@RequiredArgsConstructor
public class UsuarioController {
    private final UsuarioService usuarioService;

    @GetMapping("/listar")
    public ResponseEntity<Map<String, Object>> listar() {
        return usuarioService.listar();
    }

//    @PostMapping("/crear")
//    public ResponseEntity<Map<String, Object>> crear(@RequestBody Usuario usuario) {
//        return usuarioService.crear(usuario);
//    }

    @PostMapping("/registrar")
    public ResponseEntity<Map<String, Object>> registrar(@RequestBody Usuario usuario) {
        return usuarioService.registrar(usuario);
    }

    @GetMapping("/buscarPorEmail/{email}")
    public ResponseEntity<Map<String, Object>> buscarPorEmail(@PathVariable String email) {
        return usuarioService.buscarPorEmail(email);
    }
}
