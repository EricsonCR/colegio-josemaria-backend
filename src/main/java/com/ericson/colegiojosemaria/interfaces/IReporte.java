package com.ericson.colegiojosemaria.interfaces;

import com.ericson.colegiojosemaria.model.Pago;
import com.itextpdf.text.DocumentException;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.ResponseEntity;

import java.io.File;
import java.util.Map;

public interface IReporte {
    File reportePago(Pago pago);
}
