package com.ericson.colegiojosemaria.interfaces;

import com.itextpdf.text.DocumentException;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.ResponseEntity;

import java.util.Map;

public interface IReporte {
    ResponseEntity<Map<String,Object>> reportePagoId(long id) throws DocumentException;
}
