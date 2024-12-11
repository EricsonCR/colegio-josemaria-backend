package com.ericson.colegiojosemaria.repository;

import com.ericson.colegiojosemaria.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
    Optional<Usuario> findOneByEmail(String email);
}
