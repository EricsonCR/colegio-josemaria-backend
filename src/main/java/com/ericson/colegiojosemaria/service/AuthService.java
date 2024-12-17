package com.ericson.colegiojosemaria.service;

import com.ericson.colegiojosemaria.dto.AuthDto;
import com.ericson.colegiojosemaria.dto.UserDetailsDto;
import com.ericson.colegiojosemaria.interfaces.IAuth;
import com.ericson.colegiojosemaria.interfaces.IJwt;
import com.ericson.colegiojosemaria.model.Usuario;
import com.ericson.colegiojosemaria.repository.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class AuthService implements IAuth {
    private final JwtService jwtService;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final UsuarioRepository usuarioRepository;

    @Override
    public ResponseEntity<Map<String, Object>> login(AuthDto authDto) {
        Map<String, Object> response = new HashMap<>();
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authDto.getEmail(), authDto.getPassword()));
        Usuario usuario = usuarioRepository.findOneByEmail(authDto.getEmail()).orElse(new Usuario());
        String token = jwtService.getToken(new UserDetailsDto(usuario.getEmail(), usuario.getPassword()));
        response.put("token", token);
        return ResponseEntity.ok(response);
    }

    @Override
    public ResponseEntity<Map<String, Object>> register(AuthDto authDto) {
        Map<String, Object> response = new HashMap<>();
        Usuario usuario = new Usuario();
        usuario.setEmail(authDto.getEmail());
        usuario.setPassword(passwordEncoder.encode(authDto.getPassword()));
        usuario.setNombre(authDto.getNombre());
        usuario.setApellido(authDto.getApellido());
        usuarioRepository.save(usuario);
        String token = jwtService.getToken(new UserDetailsDto(usuario.getEmail(), usuario.getPassword()));
        response.put("token", token);
        return ResponseEntity.ok(response);
    }
}
