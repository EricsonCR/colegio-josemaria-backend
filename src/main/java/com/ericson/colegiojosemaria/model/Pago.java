package com.ericson.colegiojosemaria.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "pagos")
public class Pago {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private long id_matricula;
    private double monto;
    private String metodo_pago;
    private String numero_op;
    private String url_voucher;
    private Date registro;
    private Date actualizacion;
    private String estado;

    @Transient
    private List<PagoDetalle> pagoDetalle;

    @PrePersist
    protected void onCreate() {
        registro = new Date();
        estado = "GENERADO";
    }

    @PreUpdate
    protected void onUpdate() {
        actualizacion = new Date();
    }
}
