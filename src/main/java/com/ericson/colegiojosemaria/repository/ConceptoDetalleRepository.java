package com.ericson.colegiojosemaria.repository;

import com.ericson.colegiojosemaria.model.ConceptoDetalle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ConceptoDetalleRepository extends JpaRepository<ConceptoDetalle, Long> {
}
