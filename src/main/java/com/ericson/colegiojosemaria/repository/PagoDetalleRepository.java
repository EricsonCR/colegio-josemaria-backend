package com.ericson.colegiojosemaria.repository;

import com.ericson.colegiojosemaria.model.PagoDetalle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PagoDetalleRepository extends JpaRepository<PagoDetalle, Long> {
}
