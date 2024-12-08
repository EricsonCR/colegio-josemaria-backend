package com.ericson.colegiojosemaria.service;

import com.ericson.colegiojosemaria.interfaces.IApoderado;
import com.ericson.colegiojosemaria.model.Apoderado;
import com.ericson.colegiojosemaria.repository.ApoderadoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@RequiredArgsConstructor
public class ApoderadoService implements IApoderado {
    private final ApoderadoRepository apoderadoRepository;

    @Override
    public ResponseEntity<Map<String, Object>> listar() {
        Map<String, Object> response = new HashMap<>();
        try {
            response.put("data", apoderadoRepository.findAll());
            response.put("message", "Lista de apoderados encontrados");
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
    public ResponseEntity<Map<String, Object>> crear(Apoderado apoderado) {
        Map<String, Object> response = new HashMap<>();
        try {
            String message = validarEstudiante(apoderado);
            if (message.equals("OK")) {
                response.put("data", apoderadoRepository.save(apoderado));
                response.put("message", "Apoderado creado correctamente");
                response.put("status", HttpStatus.CREATED);
                return ResponseEntity.status(HttpStatus.CREATED).body(response);
            }
            response.put("data", new Apoderado());
            response.put("message", message);
        } catch (Exception e) {
            response.put("data", new Apoderado());
            response.put("message", e.toString());
        }
        response.put("status", HttpStatus.INTERNAL_SERVER_ERROR);
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
    }

    @Override
    public ResponseEntity<Map<String, Object>> buscarPorId(long id) {
        Map<String, Object> response = new HashMap<>();
        List<Apoderado> lista = new ArrayList<>();
        try {
            Optional<Apoderado> apoderado = apoderadoRepository.findById(id);
            if (apoderado.isPresent()) {
                lista.add(apoderado.get());
                response.put("data", lista);
                response.put("message", "Apoderado encontrado");
                response.put("status", HttpStatus.OK);
                return ResponseEntity.status(HttpStatus.OK).body(response);
            }
            response.put("data", lista);
            response.put("message", "Apoderado nao encontrado");
            response.put("status", HttpStatus.NOT_FOUND);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        } catch (Exception e) {
            response.put("data", lista);
            response.put("message", e.toString());
        }
        response.put("status", HttpStatus.INTERNAL_SERVER_ERROR);
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
    }

    private String validarEstudiante(Apoderado apoderado) {
        return "OK";
    }
}
