package com.ericson.colegiojosemaria.service;

import com.ericson.colegiojosemaria.dto.EmailDto;
import com.ericson.colegiojosemaria.interfaces.IPago;
import com.ericson.colegiojosemaria.model.Estudiante;
import com.ericson.colegiojosemaria.model.Matricula;
import com.ericson.colegiojosemaria.model.Pago;
import com.ericson.colegiojosemaria.model.PagoDetalle;
import com.ericson.colegiojosemaria.repository.EstudianteRepository;
import com.ericson.colegiojosemaria.repository.MatriculaRepository;
import com.ericson.colegiojosemaria.repository.PagoDetalleRepository;
import com.ericson.colegiojosemaria.repository.PagoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.io.File;
import java.util.*;

@Service
@RequiredArgsConstructor
public class PagoService implements IPago {
    private final PagoRepository pagoRepository;
    private final PagoDetalleRepository pagoDetalleRepository;
    private final MatriculaRepository matriculaRepository;
    private final EstudianteRepository estudianteRepository;
    private final EmailService emailService;
    private final ReporteService reporteService;

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

                EmailDto emailDto = new EmailDto();
                emailDto.setSubject("Comprobante de Pago");
                emailDto.setBody("");
                File file = reporteService.reportePago(myPago);
                Optional<Matricula> optional = matriculaRepository.findById(myPago.getId_matricula());
                if(optional.isPresent()) {
                    Matricula matricula = optional.get();
                    Optional<Estudiante> opt = estudianteRepository.findById((long) matricula.getId_estudiante());
                    if(opt.isPresent()) {
                        Estudiante estudiante = opt.get();
                        emailDto.setToUser(estudiante.getEmail());
                    }
                }
                emailService.sendEmail(emailDto, file);
                lista.add(myPago);
                //response.put("data", lista);
                response.put("message", "successful payment");
                response.put("status", HttpStatus.OK.value());
                return ResponseEntity.ok(response);
            }
            //response.put("data", lista);
            response.put("message", message);
            response.put("status", HttpStatus.INTERNAL_SERVER_ERROR.value());
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            //response.put("data", lista);
            response.put("status", HttpStatus.INTERNAL_SERVER_ERROR.value());
            response.put("message", e.getMessage());
            return ResponseEntity.ok(response);
        }
    }

    @Override
    public ResponseEntity<Map<String, Object>> buscarPorId(long id) {
        Map<String, Object> response = new HashMap<>();
        List<Pago> lista = new ArrayList<>();
        try {
            Optional<Pago> pago = pagoRepository.findById(id);
            if(pago.isPresent()) {
                lista.add(pago.get());
                response.put("data", lista);
                response.put("message", "Lista de pagos");
                response.put("status", HttpStatus.OK.value());

            }
            response.put("data", lista);
            response.put("message", "Pago no encontrado");
            response.put("status", HttpStatus.NOT_FOUND.value());
        } catch (Exception e) {
            response.put("data", lista);
            response.put("message", e.getMessage());
            response.put("status", HttpStatus.INTERNAL_SERVER_ERROR.value());
        }
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    private String validarPago(Pago pago) {
        return "OK";
    }
}
