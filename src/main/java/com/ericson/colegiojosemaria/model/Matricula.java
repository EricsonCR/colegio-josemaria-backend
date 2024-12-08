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
@Table(name = "matriculas")
public class Matricula {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private int id_estudiante;
    private String periodo;
    private String nivel;
    private String grado;
    private String seccion;
    private String situacion;
    private String descripcion;
    private Date registro;
    private Date actualizacion;
    private String estado;

    @PrePersist
    void prePersist() {
        registro = new Date();
        estado = "GENERADO";
    }

    @PreUpdate
    void preUpdate() {
        actualizacion = new Date();
    }
}
