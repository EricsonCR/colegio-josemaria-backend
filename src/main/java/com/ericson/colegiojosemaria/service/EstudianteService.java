package com.ericson.colegiojosemaria.service;

import com.ericson.colegiojosemaria.interfaces.IEstudiante;
import com.ericson.colegiojosemaria.model.Estudiante;
import com.ericson.colegiojosemaria.repository.EstudianteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@RequiredArgsConstructor
public class EstudianteService implements IEstudiante {
    private final EstudianteRepository estudianteRepository;

    @Override
    public ResponseEntity<Map<String, Object>> listar() {
        Map<String, Object> response = new HashMap<>();
        try {
            response.put("data", estudianteRepository.findAll());
            response.put("message", "Lista de estudiantes encontrados");
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
    public ResponseEntity<Map<String, Object>> crear(Estudiante estudiante) {
        Map<String, Object> response = new HashMap<>();
        try {
            String message = validarEstudiante(estudiante);
            if (message.equals("OK")) {
                response.put("data", estudianteRepository.save(estudiante));
                response.put("message", "Estudiante creado correctamente");
                response.put("status", HttpStatus.CREATED);
                return ResponseEntity.status(HttpStatus.CREATED).body(response);
            }
            response.put("data", new Estudiante());
            response.put("message", message);
        } catch (Exception e) {
            response.put("data", new Estudiante());
            response.put("message", e.toString());
        }
        response.put("status", HttpStatus.INTERNAL_SERVER_ERROR);
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
    }

    @Override
    public ResponseEntity<Map<String, Object>> buscarPorId(long id) {
        Map<String, Object> response = new HashMap<>();
        List<Estudiante> lista = new ArrayList<>();
        try {
            Optional<Estudiante> estudiante = estudianteRepository.findById(id);
            if (estudiante.isPresent()) {
                lista.add(estudiante.get());
                response.put("data", lista);
                response.put("message", "Estudiante encontrado");
                response.put("status", HttpStatus.OK);
                return ResponseEntity.status(HttpStatus.OK).body(response);
            } else {
                response.put("data", new ArrayList<>());
                response.put("message", "Estudiante no encontrado");
                response.put("status", HttpStatus.NOT_FOUND);
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
            }
        } catch (Exception e) {
            response.put("data", new ArrayList<>());
            response.put("message", e.toString());
        }
        response.put("status", HttpStatus.INTERNAL_SERVER_ERROR);
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
    }

    @Override
    public ResponseEntity<Map<String, Object>> buscarPorNumero(String numero) {
        Map<String, Object> response = new HashMap<>();
        List<Estudiante> lista = new ArrayList<>();
        try {
            Optional<Estudiante> estudiante = estudianteRepository.buscarPorNumero(numero);
            if (estudiante.isPresent()) {
                lista.add(estudiante.get());
                response.put("data", lista);
                response.put("message", "Estudiante encontrado");
                response.put("status", HttpStatus.OK);
                return ResponseEntity.status(HttpStatus.OK).body(response);
            }
            response.put("data", new ArrayList<>());
            response.put("message", "No existe estudiante");
            response.put("status", HttpStatus.NOT_FOUND);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        } catch (Exception e) {
            response.put("data", new ArrayList<>());
            response.put("message", e.toString());
        }
        response.put("status", HttpStatus.INTERNAL_SERVER_ERROR);
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
    }

    private String validarEstudiante(Estudiante estudiante) {
        return "OK";
    }
}
