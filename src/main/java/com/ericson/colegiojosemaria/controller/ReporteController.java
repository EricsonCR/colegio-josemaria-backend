package com.ericson.colegiojosemaria.controller;

import com.ericson.colegiojosemaria.dto.EmailDto;
import com.ericson.colegiojosemaria.model.Estudiante;
import com.ericson.colegiojosemaria.model.Matricula;
import com.ericson.colegiojosemaria.model.Pago;
import com.ericson.colegiojosemaria.model.PagoDetalle;
import com.ericson.colegiojosemaria.repository.EstudianteRepository;
import com.ericson.colegiojosemaria.repository.MatriculaRepository;
import com.ericson.colegiojosemaria.repository.PagoDetalleRepository;
import com.ericson.colegiojosemaria.repository.PagoRepository;
import com.ericson.colegiojosemaria.service.EmailService;
import com.ericson.colegiojosemaria.service.ReporteService;
import com.itextpdf.text.DocumentException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/reporte")
@RequiredArgsConstructor
public class ReporteController {
    private final ReporteService reporteService;
    private final PagoDetalleRepository pagoDetalleRepository;
    private final PagoRepository pagoRepository;
    private final MatriculaRepository matriculaRepository;
    private final EstudianteRepository estudianteRepository;
    private final EmailService emailService;

    @GetMapping("/pagoPorId/{id}")
    public ResponseEntity<Map<String, Object>> pagoPorId(@PathVariable long id) {
        Map<String, Object> response = new HashMap<>();

        Pago pago = null;
        Optional<Pago> optionalPago = pagoRepository.findById(id);
        if (optionalPago.isPresent()) {
            pago = optionalPago.get();
            List<PagoDetalle> pagoDetalle = pagoDetalleRepository.listarPorIdPago(id);
            pago.setPagoDetalle(pagoDetalle);
        } else {
            response.put("mensaje", "Payment not found");
            response.put("status", HttpStatus.INTERNAL_SERVER_ERROR.value());
            return ResponseEntity.ok().body(response);
        }

        Estudiante estudiante = null;
        Optional<Matricula> optionalMatricula = matriculaRepository.findById(pago.getId_matricula());
        if (optionalMatricula.isPresent()) {
            Matricula matricula = optionalMatricula.get();
            Optional<Estudiante> optionalEstudiante = estudianteRepository.findById((long) matricula.getId_estudiante());
            if (optionalEstudiante.isPresent()) {
                estudiante = optionalEstudiante.get();
                if (estudiante.getEmail().isEmpty()) {
                    response.put("mensaje", "Email not found");
                    response.put("status", HttpStatus.INTERNAL_SERVER_ERROR.value());
                    return ResponseEntity.ok().body(response);
                }
            } else {
                response.put("mensaje", "Student not found");
                response.put("status", HttpStatus.INTERNAL_SERVER_ERROR.value());
                return ResponseEntity.ok().body(response);
            }
        } else {
            response.put("mensaje", "Matricula not found");
            response.put("status", HttpStatus.INTERNAL_SERVER_ERROR.value());
            return ResponseEntity.ok().body(response);
        }

        File file = reporteService.reportePago(pago);
        if (file != null) {
            EmailDto emailDto = new EmailDto();
            emailDto.setToUser(estudiante.getEmail());
            emailDto.setSubject("Reporte de pago");
            emailDto.setBody("");
            if (emailService.sendEmail(emailDto, file)) {
                response.put("mensaje", "Email send successfully");
                response.put("status", HttpStatus.OK.value());
                return ResponseEntity.ok().body(response);
            } else {
                response.put("mensaje", "Error when sending email");
                response.put("status", HttpStatus.INTERNAL_SERVER_ERROR.value());
                return ResponseEntity.ok().body(response);
            }
        }
        response.put("mensaje", "Error generating pdf");
        response.put("status", HttpStatus.INTERNAL_SERVER_ERROR.value());

        return ResponseEntity.ok().body(response);
    }
}
