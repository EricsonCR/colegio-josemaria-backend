package com.ericson.colegiojosemaria.service;

import com.ericson.colegiojosemaria.interfaces.IUsuario;
import com.ericson.colegiojosemaria.model.Usuario;
import com.ericson.colegiojosemaria.repository.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UsuarioService implements IUsuario {
    private final UsuarioRepository usuarioRepository;

    @Override
    public ResponseEntity<Map<String, Object>> listar() {
        Map<String, Object> response = new HashMap<>();
        try {
            response.put("data", usuarioRepository.findAll());
            response.put("message", "Lista de usuarios encontrados");
            response.put("status", HttpStatus.OK);
            return ResponseEntity.status(HttpStatus.OK).body(response);
        } catch (Exception e) {
            response.put("data", new ArrayList<>());
            response.put("message", e.getMessage());
            response.put("status", HttpStatus.INTERNAL_SERVER_ERROR);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }

    @Override
    public ResponseEntity<Map<String, Object>> crear(Usuario usuario) {
        Map<String, Object> response = new HashMap<>();
        try {
            String message = validarUsuario(usuario);
            if (message.equals("OK")) {
                Usuario user = usuarioRepository.save(usuario);
                response.put("data", user);
                response.put("message", "Usuario agregado correctamente");
                response.put("status", HttpStatus.CREATED.value());
                return ResponseEntity.status(HttpStatus.CREATED).body(response);
            }
            response.put("data", new Usuario());
            response.put("message", message);
            response.put("status", HttpStatus.INTERNAL_SERVER_ERROR);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        } catch (Exception e) {
            response.put("data", new Usuario());
            response.put("message", e.toString());
            response.put("status", HttpStatus.INTERNAL_SERVER_ERROR);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }

    @Override
    public Optional<Usuario> obtenerUsuarioPorEmail(String email) {
        return usuarioRepository.findOneByEmail(email);
    }

    @Override
    public ResponseEntity<Map<String, Object>> registrar(Usuario usuario) {
        Map<String, Object> response = new HashMap<>();
        try {
            String message = validarUsuario(usuario);
            if (message.equals("OK")) {
                Usuario user = usuarioRepository.save(usuario);
                response.put("data", user);
                response.put("message", "Usuario registrado correctamente");
                response.put("status", HttpStatus.CREATED.value());
            } else {
                response.put("data", new Usuario());
                response.put("message", message);
                response.put("status", HttpStatus.UNPROCESSABLE_ENTITY.value());
            }
        } catch (Exception e) {
            response.put("data", new Usuario());
            response.put("message", e.toString());
            response.put("status", HttpStatus.INTERNAL_SERVER_ERROR.value());
        }
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    private String validarUsuario(Usuario usuario) {
        return "OKK";
    }
}
