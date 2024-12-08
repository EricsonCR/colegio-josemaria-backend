package com.ericson.colegiojosemaria.service;

import com.ericson.colegiojosemaria.interfaces.IConceptoDetalle;
import com.ericson.colegiojosemaria.model.ConceptoDetalle;
import com.ericson.colegiojosemaria.repository.ConceptoDetalleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class ConceptoDetalleService implements IConceptoDetalle {
    private final ConceptoDetalleRepository conceptoDetalleRepository;

    @Override
    public ResponseEntity<Map<String, Object>> listar() {
        Map<String, Object> response = new HashMap<>();
        try {
            response.put("data", conceptoDetalleRepository.findAll());
            response.put("message", "Listado de Conceptos Detalles");
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
    public ResponseEntity<Map<String, Object>> crear(ConceptoDetalle conceptoDetalle) {
        Map<String, Object> response = new HashMap<>();
        try {
            String message = validarConceptoDetalle(conceptoDetalle);
            if (message.equals("OK")) {
                response.put("data", conceptoDetalleRepository.save(conceptoDetalle));
                response.put("message", "ConceptoDetalle creado correctamente");
                response.put("status", HttpStatus.CREATED);
                return ResponseEntity.status(HttpStatus.CREATED).body(response);
            }
            response.put("data", new ConceptoDetalle());
            response.put("message", message);
        } catch (Exception e) {
            response.put("data", new ConceptoDetalle());
            response.put("message", e.toString());
        }
        response.put("status", HttpStatus.INTERNAL_SERVER_ERROR);
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
    }

    private String validarConceptoDetalle(ConceptoDetalle conceptoDetalle) {
        return "OK";
    }
}
