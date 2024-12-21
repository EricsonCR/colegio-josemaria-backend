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
    public boolean sendEmail(EmailDto emailDto) {
        try {
            SimpleMailMessage mailMessage = new SimpleMailMessage();
            mailMessage.setFrom(userFrom);
            mailMessage.setTo(emailDto.getToUser());
            mailMessage.setSubject(emailDto.getSubject());
            mailMessage.setText(emailDto.getBody());
            mailSender.send(mailMessage);
            return true;
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return false;
    }

    @Override
    public boolean sendEmail(EmailDto emailDto, File file) {
        try {
            MimeMessage mimeMessage = mailSender.createMimeMessage();
            String encode = StandardCharsets.UTF_8.name();
            MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage, true, encode);
            mimeMessageHelper.setFrom(userFrom);
            mimeMessageHelper.setTo(emailDto.getToUser());
            mimeMessageHelper.setSubject(emailDto.getSubject());
            mimeMessageHelper.setText(emailDto.getBody(), true);
            mimeMessageHelper.addAttachment(file.getName(), file);
            mailSender.send(mimeMessage);
            return true;
        } catch (MessagingException e) {
            System.out.println(e.getMessage());
        }
        return false;
    }
}
