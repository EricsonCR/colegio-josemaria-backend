package com.ericson.colegiojosemaria.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "usuarios")
public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String rol;
    private String documento;
    private String numero;
    private String nombre;
    private String apellido;
    private String direccion;
    private String telefono;
    private String genero;
    private String email;
    private String password;
    private Date nacimiento;
    private Date registro;
    private Date actualizacion;
    private boolean estado;

    @PrePersist
    void prePersist() {
        registro = new Date();
        actualizacion = new Date();
        estado = true;
    }

    @PreUpdate
    void preUpdate() {
        actualizacion = new Date();
    }
}
