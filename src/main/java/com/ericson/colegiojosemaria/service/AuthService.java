package com.ericson.colegiojosemaria.service;

import com.ericson.colegiojosemaria.dto.AuthDto;
import com.ericson.colegiojosemaria.dto.EmailDto;
import com.ericson.colegiojosemaria.dto.UserDetailsDto;
import com.ericson.colegiojosemaria.interfaces.IAuth;
import com.ericson.colegiojosemaria.interfaces.IEmail;
import com.ericson.colegiojosemaria.model.Usuario;
import com.ericson.colegiojosemaria.repository.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@RequiredArgsConstructor
public class AuthService implements IAuth {
    private final JwtService jwtService;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final UsuarioRepository usuarioRepository;
    private final IEmail emailService;

    private final Set<AuthDto> validations = new HashSet<>();

    @Override
    public ResponseEntity<Map<String, Object>> login(AuthDto authDto) {
        Map<String, Object> response = new HashMap<>();
        Map<String, Object> data = new HashMap<>();
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authDto.getEmail(), authDto.getPassword()));
            Usuario usuario = usuarioRepository.findOneByEmail(authDto.getEmail()).orElse(new Usuario());
            String token = jwtService.getToken(new UserDetailsDto(usuario.getEmail(), usuario.getPassword()));
            data.put("token", token);
            data.put("nombre", usuario.getNombre());
            data.put("apellido", usuario.getApellido());
            data.put("email", usuario.getEmail());
            response.put("data", data);
            response.put("message", "Authentication Successful");
            response.put("status", HttpStatus.OK.value());
        } catch (Exception e) {
            response.put("data", new HashMap<>());
            response.put("message", e.getMessage());
            response.put("status", HttpStatus.INTERNAL_SERVER_ERROR.value());
        }
        return ResponseEntity.ok(response);
    }

    @Override
    public ResponseEntity<Map<String, Object>> generated(AuthDto authDto) {
        Map<String, Object> response = new HashMap<>();
        String message = validations(authDto);
        if (message.equals("OK")) {
            Optional<Usuario> optional = usuarioRepository.findOneByEmail(authDto.getEmail());
            if (optional.isEmpty()) {
                Random rand = new Random();
                int code = 100000 + rand.nextInt(900000);
                authDto.setCode(Integer.toString(code));
                EmailDto emailDto = new EmailDto();
                emailDto.setToUser(authDto.getEmail());
                emailDto.setSubject("Generacion de codigo para validar registro de usuario");
                emailDto.setBody(generatedBodyEmail(authDto));
                validations.add(authDto);
                if (emailService.sendEmail(emailDto)) {
                    response.put("message", "Email send successful");
                    response.put("status", HttpStatus.OK.value());
                    return ResponseEntity.ok(response);
                } else {
                    response.put("message", "Email send failed");
                    response.put("status", HttpStatus.INTERNAL_SERVER_ERROR.value());
                    return ResponseEntity.ok(response);
                }
            }
            response.put("data", new HashMap<>());
            response.put("message", "Email already exists");
            response.put("status", HttpStatus.FOUND.value());
            return ResponseEntity.ok(response);
        }
        response.put("data", new HashMap<>());
        response.put("message", message);
        response.put("status", HttpStatus.INTERNAL_SERVER_ERROR.value());
        return ResponseEntity.ok(response);
    }

    private String validations(AuthDto authDto) {
        String email = authDto.getEmail();
        if (email.contains("@") && email.contains(".com")) {
            return "OK";
        }
        return "Email field is not correct";
    }

    @Override
    public ResponseEntity<Map<String, Object>> register(String code) {
        Map<String, Object> response = new HashMap<>();
        Map<String, Object> data = new HashMap<>();
        Usuario usuario = new Usuario();
        try {
            for (AuthDto authDto : validations) {
                if (authDto.getCode().equals(code)) {
                    usuario.setDocumento(authDto.getDocumento());
                    usuario.setNumero(authDto.getNumero());
                    usuario.setNombre(authDto.getNombre());
                    usuario.setApellido(authDto.getApellido());
                    usuario.setEmail(authDto.getEmail());
                    usuario.setPassword(passwordEncoder.encode(authDto.getPassword()));
                    usuarioRepository.save(usuario);
                    String token = jwtService.getToken(new UserDetailsDto(usuario.getEmail(), usuario.getPassword()));
                    data.put("token", token);
                    data.put("nombre", usuario.getNombre());
                    data.put("apellido", usuario.getApellido());
                    data.put("email", usuario.getEmail());
                    response.put("data", data);
                    response.put("message", "Successfully registered user");
                    response.put("status", HttpStatus.OK.value());
                    return ResponseEntity.ok(response);
                }
            }
            response.put("message", "This code is not related to any user");
            response.put("status", HttpStatus.NOT_FOUND.value());
        } catch (Exception e) {
            System.out.println(e.getMessage());
            response.put("message", "Error internal");
            response.put("status", HttpStatus.INTERNAL_SERVER_ERROR.value());
        }
        return ResponseEntity.ok(response);
    }

    private String generatedBodyEmail(AuthDto authDto) {
        return "Hola " + authDto.getNombre() + ", este es tu codigo: " + authDto.getCode() + " para validar tu registro";
    }
}
