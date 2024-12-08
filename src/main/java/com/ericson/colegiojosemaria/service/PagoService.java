package com.ericson.colegiojosemaria.service;

import com.ericson.colegiojosemaria.interfaces.IPago;
import com.ericson.colegiojosemaria.model.Pago;
import com.ericson.colegiojosemaria.model.PagoDetalle;
import com.ericson.colegiojosemaria.repository.PagoDetalleRepository;
import com.ericson.colegiojosemaria.repository.PagoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class PagoService implements IPago {
    private final PagoRepository pagoRepository;
    private final PagoDetalleRepository pagoDetalleRepository;

    @Override
    public ResponseEntity<Map<String, Object>> listar() {
        Map<String, Object> response = new HashMap<>();
        List<Pago> lista = new ArrayList<>();
        try {
            List<Pago> pagos = pagoRepository.findAll();
            lista.addAll(pagos);
            response.put("data", lista);
            response.put("message", "Lista de pagos");
            response.put("status", HttpStatus.OK);
            return ResponseEntity.status(HttpStatus.OK).body(response);
        } catch (Exception e) {
            response.put("data", lista);
            response.put("status", HttpStatus.INTERNAL_SERVER_ERROR);
            response.put("message", e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }

    @Override
    public ResponseEntity<Map<String, Object>> crear(Pago pago) {
        Map<String, Object> response = new HashMap<>();
        List<Pago> lista = new ArrayList<>();
        try {
            String message = validarPago(pago);
            if (message.equals("OK")) {
                Pago myPago = pagoRepository.save(pago);
                pago.getPagoDetalle().forEach(pagoDetalle -> {
                    pagoDetalle.setId_pago(myPago.getId());
                });
                List<PagoDetalle> pagoDetalle = pagoDetalleRepository.saveAll(pago.getPagoDetalle());
                myPago.setPagoDetalle(pagoDetalle);
                lista.add(myPago);
                response.put("data", lista);
                response.put("message", "Pago creado correctamente");
                response.put("status", HttpStatus.CREATED);
                return ResponseEntity.status(HttpStatus.CREATED).body(response);
            }
            response.put("data", lista);
            response.put("message", message);
            response.put("status", HttpStatus.INTERNAL_SERVER_ERROR);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        } catch (Exception e) {
            response.put("data", lista);
            response.put("status", HttpStatus.INTERNAL_SERVER_ERROR);
            response.put("message", e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }

    private String validarPago(Pago pago) {
        return "OK";
    }
}
