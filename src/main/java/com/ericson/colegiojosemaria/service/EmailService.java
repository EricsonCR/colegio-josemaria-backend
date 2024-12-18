package com.ericson.colegiojosemaria.service;

import com.ericson.colegiojosemaria.dto.EmailDto;
import com.ericson.colegiojosemaria.interfaces.IEmail;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import java.io.File;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class EmailService implements IEmail {
    private final JavaMailSender mailSender;

    @Value("${email.username}")
    private String userFrom;

    @Override
    public ResponseEntity<Map<String, Object>> sendEmail(EmailDto emailDto) {
        Map<String, Object> response = new HashMap<>();
        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setFrom(userFrom);
        mailMessage.setTo(emailDto.getToUser());
        mailMessage.setSubject(emailDto.getSubject());
        mailMessage.setText(emailDto.getBody());
        try {
            mailSender.send(mailMessage);
            response.put("message", "Email send successful");
            response.put("status", HttpStatus.OK.value());
        } catch (Exception e) {
            response.put("message", e.getMessage());
            response.put("status", 0);
        }
        return ResponseEntity.ok(response);
    }

    @Override
    public ResponseEntity<Map<String, Object>> sendEmailWithFile(EmailDto emailDto, File file) {
        Map<String, Object> response = new HashMap<>();
        MimeMessage mimeMessage = mailSender.createMimeMessage();
        String encode = StandardCharsets.UTF_8.name();
        try {
            MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage, true, encode);
            mimeMessageHelper.setFrom(userFrom);
            mimeMessageHelper.setTo(emailDto.getToUser());
            mimeMessageHelper.setSubject(emailDto.getSubject());
            mimeMessageHelper.setText(emailDto.getBody(), true);
            mimeMessageHelper.addAttachment(file.getName(), file);
            mailSender.send(mimeMessage);
            response.put("message", "Email with file send successful");
            response.put("file", file.exists() ? file.getName() : "");
            return ResponseEntity.ok(response);
        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
    }
}
