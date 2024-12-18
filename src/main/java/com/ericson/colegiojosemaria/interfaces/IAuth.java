package com.ericson.colegiojosemaria.interfaces;

import com.ericson.colegiojosemaria.dto.AuthDto;
import org.springframework.http.ResponseEntity;

import java.util.Map;

public interface IAuth {
    ResponseEntity<Map<String, Object>> login(AuthDto authDto);

    ResponseEntity<Map<String, Object>> generated(AuthDto authDto);

    ResponseEntity<Map<String, Object>> register(String code);
}
