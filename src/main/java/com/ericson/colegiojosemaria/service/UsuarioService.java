package com.ericson.colegiojosemaria.service;

import com.ericson.colegiojosemaria.interfaces.IUsuario;
import com.ericson.colegiojosemaria.model.Usuario;
import com.ericson.colegiojosemaria.repository.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@RequiredArgsConstructor
public class UsuarioService implements IUsuario {
    private final UsuarioRepository usuarioRepository;
    private final PasswordEncoder passwordEncoder;

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
    public ResponseEntity<Map<String, Object>> buscarPorEmail(String email) {
        Map<String, Object> response = new HashMap<>();
        List<Usuario> lista = new ArrayList<>();
        try {
            Optional<Usuario> optional = usuarioRepository.findOneByEmail(email);
            if (optional.isPresent()) {
                lista.add(optional.get());
                response.put("data", lista);
                response.put("message", "Usuario encontrado");
                response.put("status", HttpStatus.OK.value());
            }
        } catch (Exception e) {
            response.put("data", new ArrayList<>());
            response.put("message", e.getMessage());
            response.put("status", HttpStatus.INTERNAL_SERVER_ERROR.value());
        }
        return ResponseEntity.ok(response);
    }

    @Override
    public ResponseEntity<Map<String, Object>> registrar(Usuario usuario) {
        Map<String, Object> response = new HashMap<>();
        try {
            String message = validarUsuario(usuario);
            if (message.equals("OK")) {
                usuario.setPassword(passwordEncoder.encode(usuario.getPassword()));
                usuarioRepository.save(usuario);
                response.put("message", "successfully registered user");
                response.put("status", HttpStatus.OK.value());
            } else {
                response.put("message", message);
                response.put("status", HttpStatus.INTERNAL_SERVER_ERROR.value());
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
            response.put("message", "Error internal server");
            response.put("status", HttpStatus.INTERNAL_SERVER_ERROR.value());
        }
        return ResponseEntity.ok(response);
    }

    private String validarUsuario(Usuario usuario) {
        Optional<Usuario> optional = usuarioRepository.findOneByNumero(usuario.getNumero());
        if (optional.isPresent()) {
            return "User already exists with the same DNI number";
        }

        optional = usuarioRepository.findOneByEmail(usuario.getEmail());
        if (optional.isPresent()) {
            return "User already exists with the same email";
        }
        return "OK";
    }
}
