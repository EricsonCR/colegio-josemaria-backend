package com.ericson.colegiojosemaria.interfaces;

import com.ericson.colegiojosemaria.dto.EmailDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.Map;

public interface IEmail {
    ResponseEntity<Map<String, Object>> sendEmail(EmailDto emailDto);

    ResponseEntity<Map<String, Object>> sendEmailWithFile(EmailDto emailDto, File file);
}
