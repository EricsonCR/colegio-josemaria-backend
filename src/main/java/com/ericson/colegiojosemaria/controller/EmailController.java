package com.ericson.colegiojosemaria.controller;

import com.ericson.colegiojosemaria.dto.EmailDto;
import com.ericson.colegiojosemaria.interfaces.IEmail;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/email")
@RequiredArgsConstructor
public class EmailController {
    private final IEmail emailService;

    @PostMapping("/sendMessage")
    public ResponseEntity<Map<String, Object>> sendEmail(@RequestBody EmailDto emailDto) {
        Map<String, Object> response = new HashMap<>();
        if (emailService.sendEmail(emailDto)) {
            response.put("message", "Send email successfully");
            response.put("status", HttpStatus.OK.value());
        } else {
            response.put("message", "Failed to send email");
            response.put("status", HttpStatus.INTERNAL_SERVER_ERROR.value());
        }
        return ResponseEntity.ok(response);
    }

    @PostMapping("/sendMessageFile")
    public ResponseEntity<Map<String, Object>> sendEmailWithFile(@ModelAttribute EmailDto emailDto) {
        Map<String, Object> response = new HashMap<>();
        try {
            String fileName = emailDto.getFile().getOriginalFilename();
            Path path = Paths.get("src/main/resource/files/" + fileName);

            Files.createDirectories(path.getParent());
            Files.copy(emailDto.getFile().getInputStream(), path, StandardCopyOption.REPLACE_EXISTING);
            File file = path.toFile();

            emailService.sendEmail(emailDto, file);

            response.put("message", "Send email successfully");
            response.put("status", HttpStatus.OK.value());


        } catch (Exception e) {
            response.put("message", "Failed to send email: " + e.getMessage());
            response.put("status", HttpStatus.INTERNAL_SERVER_ERROR.value());
        }
        return ResponseEntity.ok(response);
    }
}
