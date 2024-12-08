package com.ericson.colegiojosemaria.service;

import com.ericson.colegiojosemaria.interfaces.IMatriculaDetalle;
import com.ericson.colegiojosemaria.repository.IMatriculaDetalleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class MatriculaDetalleService implements IMatriculaDetalle {
    private final IMatriculaDetalleRepository matriculaDetalleRepository;

    @Override
    public ResponseEntity<Map<String, Object>> listar() {
        return null;
    }

    @Override
    public ResponseEntity<Map<String, Object>> listarPorIdMatricula(long id) {
        Map<String, Object> response = new HashMap<>();
        try {
            response.put("data", matriculaDetalleRepository.listarPorIdMatricula(id));
            response.put("message", "Lista de matriculas detalle");
            response.put("status", HttpStatus.OK);
            return ResponseEntity.status(HttpStatus.OK).body(response);
        } catch (Exception e) {
            response.put("data", new ArrayList<>());
            response.put("message", e.toString());
            response.put("status", HttpStatus.INTERNAL_SERVER_ERROR);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }
}
