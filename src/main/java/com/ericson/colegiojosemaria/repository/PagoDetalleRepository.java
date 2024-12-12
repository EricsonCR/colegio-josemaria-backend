package com.ericson.colegiojosemaria.repository;

import com.ericson.colegiojosemaria.model.PagoDetalle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PagoDetalleRepository extends JpaRepository<PagoDetalle, Long> {
    @Query(value = "select * from pagos_detalle pd where id_pago=:id", nativeQuery = true)
    List<PagoDetalle> listarPorIdPago(Long id);
}
