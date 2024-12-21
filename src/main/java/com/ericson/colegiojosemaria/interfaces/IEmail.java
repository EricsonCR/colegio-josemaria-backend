package com.ericson.colegiojosemaria.interfaces;

import com.ericson.colegiojosemaria.dto.EmailDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.Map;

public interface IEmail {

    boolean sendEmail(EmailDto emailDto);

    boolean sendEmail(EmailDto emailDto, File file);
}
