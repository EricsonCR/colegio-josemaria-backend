package com.ericson.colegiojosemaria.repository;

import com.ericson.colegiojosemaria.model.MatriculaDetalle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IMatriculaDetalleRepository extends JpaRepository<MatriculaDetalle, Long> {
    @Query(value = "select * from matricula_detalle md where md.id_matricula=:id", nativeQuery = true)
    List<MatriculaDetalle> listarPorIdMatricula(long id);
}
