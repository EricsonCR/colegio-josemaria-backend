package com.ericson.colegiojosemaria.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Table(name = "pagos_detalle")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PagoDetalle {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private long id_pago;
    private long id_matricula_detalle;
    private String concepto;
    private double monto;
    private String estado;
}
