package com.ericson.colegiojosemaria.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AuthDto {
    private String nombre;
    private String apellido;
    private String email;
    private String password;
}
