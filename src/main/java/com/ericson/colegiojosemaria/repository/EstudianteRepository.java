package com.ericson.colegiojosemaria.repository;

import com.ericson.colegiojosemaria.model.Estudiante;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface EstudianteRepository extends JpaRepository<Estudiante, Long> {
    @Query(value = "select * from estudiantes e where e.numero=?1", nativeQuery = true)
    Optional<Estudiante> buscarPorNumero(String numero);
}
