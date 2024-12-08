package com.ericson.colegiojosemaria.repository;

import com.ericson.colegiojosemaria.model.Apoderado;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ApoderadoRepository extends JpaRepository<Apoderado, Long> {
}
