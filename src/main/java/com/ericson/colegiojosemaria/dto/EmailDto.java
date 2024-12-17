package com.ericson.colegiojosemaria.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EmailDto {
    private String toUser;
    private String subject;
    private String body;
    private MultipartFile file;
}
