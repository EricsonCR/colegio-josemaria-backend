package com.ericson.colegiojosemaria.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AuthDto {
    private String documento;
    private String numero;
    private String nombre;
    private String apellido;
    private String email;
    private String password;
    private String code;
    private String token;
}
