package com.ericson.colegiojosemaria.service;

import com.ericson.colegiojosemaria.interfaces.IConcepto;
import com.ericson.colegiojosemaria.model.Concepto;
import com.ericson.colegiojosemaria.repository.ConceptoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class ConceptoService implements IConcepto {
    private final ConceptoRepository conceptoRepository;

    @Override
    public ResponseEntity<Map<String, Object>> listar() {
        Map<String, Object> response = new HashMap<>();
        try {
            response.put("data", conceptoRepository.findAll());
            response.put("message", "Lista de conceptos");
            response.put("status", HttpStatus.OK);
            return ResponseEntity.status(HttpStatus.OK).body(response);
        } catch (Exception e) {
            response.put("data", new Concepto());
            response.put("message", e.getMessage());
            response.put("status", HttpStatus.INTERNAL_SERVER_ERROR);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }

    @Override
    public ResponseEntity<Map<String, Object>> crear(Concepto concepto) {
        Map<String, Object> response = new HashMap<>();
        try {
            String message = validarConcepto(concepto);
            if (message.equals("OK")) {
                response.put("data", conceptoRepository.save(concepto));
                response.put("message", "Concepto registrado correctamente");
                response.put("status", HttpStatus.CREATED);
                return ResponseEntity.status(HttpStatus.CREATED).body(response);
            }
            response.put("data", new Concepto());
            response.put("message", message);
        } catch (Exception e) {
            response.put("data", new Concepto());
            response.put("message", e.getMessage());
        }
        response.put("status", HttpStatus.INTERNAL_SERVER_ERROR.value());
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
    }

    private String validarConcepto(Concepto concepto) {
        return "OK";
    }
}
