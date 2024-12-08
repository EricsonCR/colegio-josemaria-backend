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
@Table(name = "conceptos")
public class Concepto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String nombre;
    private Date registro;
    private Date actualizacion;
    private boolean estado;

    @PrePersist
    public void prePersist() {
        this.registro = new Date();
        this.estado = true;
        this.nombre = this.nombre.toUpperCase();
    }

    @PreUpdate
    public void preUpdate() {
        this.actualizacion = new Date();
    }
}
