package com.ericson.colegiojosemaria.service;

import com.ericson.colegiojosemaria.interfaces.IMatricula;
import com.ericson.colegiojosemaria.model.Matricula;
import com.ericson.colegiojosemaria.repository.MatriculaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@RequiredArgsConstructor
public class MatriculaService implements IMatricula {

    private final MatriculaRepository matriculaRepository;

    @Override
    public ResponseEntity<Map<String, Object>> listar() {
        Map<String, Object> response = new HashMap<>();
        try {
            response.put("data", matriculaRepository.findAll());
            response.put("message", "Busqueda de matriculas correctamente");
            response.put("status", HttpStatus.OK);
            return ResponseEntity.status(HttpStatus.OK).body(response);
        } catch (Exception e) {
            response.put("data", new ArrayList<>());
            response.put("message", e.toString());
            response.put("status", HttpStatus.INTERNAL_SERVER_ERROR);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }

    @Override
    public ResponseEntity<Map<String, Object>> buscarPorId(long id) {
        Map<String, Object> response = new HashMap<>();
        List<Matricula> matriculas = new ArrayList<>();
        try {
            Optional<Matricula> matricula = matriculaRepository.findById(id);
            if (matricula.isPresent()) {
                matriculas.add(matricula.get());
                response.put("data", matriculas);
                response.put("message", "Matricula encontrada");
                response.put("status", HttpStatus.OK);
                return ResponseEntity.status(HttpStatus.OK).body(response);
            } else {
                response.put("data", new ArrayList<>());
                response.put("message", "Matricula no encontrada");
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
    public ResponseEntity<Map<String, Object>> buscarPorIdEstudiante(long id) {
        Map<String, Object> response = new HashMap<>();
        List<Matricula> lista = new ArrayList<>();
        try {
            Optional<Matricula> matricula = matriculaRepository.findById(id);
            if (matricula.isPresent()) {
                lista.add(matricula.get());
                response.put("data", lista);
                response.put("message", "Matricula encontrada");
                response.put("status", HttpStatus.OK);
                return ResponseEntity.status(HttpStatus.OK).body(response);
            }
            response.put("data", new ArrayList<>());
            response.put("message", "Matricula no encontrada");
            response.put("status", HttpStatus.NOT_FOUND);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        } catch (Exception e) {
            response.put("data", new ArrayList<>());
            response.put("message", e.toString());
            response.put("status", HttpStatus.INTERNAL_SERVER_ERROR);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }

    @Override
    public ResponseEntity<Map<String, Object>> crear(Matricula matricula) {
        Map<String, Object> response = new HashMap<>();
        try {
            String message = validarMatricula(matricula);
            if (message.equals("OK")) {
                response.put("data", matriculaRepository.save(matricula));
                response.put("message", "Matricula Creada correctamente");
                response.put("status", HttpStatus.CREATED);
                return ResponseEntity.status(HttpStatus.CREATED).body(response);
            }
            response.put("data", new Matricula());
            response.put("message", message);
        } catch (Exception e) {
            response.put("data", new Matricula());
            response.put("message", e.toString());
        }
        response.put("status", HttpStatus.INTERNAL_SERVER_ERROR);
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
    }

    private String validarMatricula(Matricula matricula) {
        return "OK";
    }
}
