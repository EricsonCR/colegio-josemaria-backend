package com.ericson.colegiojosemaria.repository;

import com.ericson.colegiojosemaria.model.Concepto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ConceptoRepository extends JpaRepository<Concepto, Long> {
}
