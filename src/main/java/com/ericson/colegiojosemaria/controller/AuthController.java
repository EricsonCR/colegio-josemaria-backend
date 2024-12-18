package com.ericson.colegiojosemaria.controller;

import com.ericson.colegiojosemaria.dto.AuthDto;
import com.ericson.colegiojosemaria.interfaces.IAuth;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@CrossOrigin(value = "http://localhost:4200")
@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final IAuth authService;

    @PostMapping("/login")
    public ResponseEntity<Map<String, Object>> login(@RequestBody AuthDto authDto) {
        return authService.login(authDto);
    }

    @PostMapping("/generated")
    public ResponseEntity<Map<String, Object>> generated(@RequestBody AuthDto authDto) {
        return authService.generated(authDto);
    }

    @PostMapping("/register")
    public ResponseEntity<Map<String, Object>> register(@RequestBody String code) {
        return authService.register(code);
    }
}
