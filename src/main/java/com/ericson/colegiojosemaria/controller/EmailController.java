package com.ericson.colegiojosemaria.controller;

import com.ericson.colegiojosemaria.dto.EmailDto;
import com.ericson.colegiojosemaria.interfaces.IEmail;
import com.ericson.colegiojosemaria.service.EmailService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Map;

@RestController
@RequestMapping("/api/email")
@RequiredArgsConstructor
public class EmailController {
    private final IEmail emailService;

    @PostMapping("/sendMessage")
    public ResponseEntity<Map<String, Object>> sendEmail(@RequestBody EmailDto emailDto) {
        return emailService.sendEmail(emailDto);
    }

    @PostMapping("/sendMessageFile")
    public ResponseEntity<Map<String, Object>> sendEmailWithFile(@ModelAttribute EmailDto emailDto) {
        try {
            String fileName = emailDto.getFile().getOriginalFilename();
            Path path = Paths.get("src/main/resource/files/" + fileName);

            Files.createDirectories(path.getParent());
            Files.copy(emailDto.getFile().getInputStream(), path, StandardCopyOption.REPLACE_EXISTING);
            File file = path.toFile();

            return emailService.sendEmailWithFile(emailDto, file);

        } catch (Exception e) {
            throw new RuntimeException(e.toString());
        }

    }
}
