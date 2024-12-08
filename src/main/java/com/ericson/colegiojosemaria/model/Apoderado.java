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
@Table(name = "apoderados")
public class Apoderado {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String documento;
    private String numero;
    private String nombre;
    private String apellido;
    private String direccion;
    private String telefono;
    private String relacion;
    private String email;
    private String password;
    private Date nacimiento;
    private Date registro;
    private Date actualizacion;
    private boolean estado;

    @PrePersist
    public void prePersist() {
        registro = new Date();
        estado = true;
    }

    @PreUpdate
    public void preUpdate() {
        actualizacion = new Date();
    }
}