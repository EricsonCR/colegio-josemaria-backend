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
@Table(name = "estudiantes")
public class Estudiante {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private int id_apoderado;
    private String documento;
    private String numero;
    private String nombre;
    private String apellido;
    private String direccion;
    private String telefono;
    private String genero;
    private String email;
    private Date nacimiento;
    private Date registro;
    private Date actualizacion;
    private boolean estado;

    @PrePersist
    public void prePersist() {
        registro = new Date();
        actualizacion = new Date();
        estado = true;
    }

    @PreUpdate
    public void preUpdate() {
        actualizacion = new Date();
    }
}
