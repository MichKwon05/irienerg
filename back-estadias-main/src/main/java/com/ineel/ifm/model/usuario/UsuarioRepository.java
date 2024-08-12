package com.ineel.ifm.model.usuario;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface UsuarioRepository extends JpaRepository<Usuario,Long> {

    @Modifying
    @Query("UPDATE Usuario u SET u.status = :status WHERE u.id = :id")
    void updateStatus(@Param("status") boolean status, @Param("id") Long id);
    @Override
    Optional<Usuario> findById(Long id);
    List<Usuario> findAllByStatus(Boolean status);
    Usuario getById(Long id);

    @Query("SELECT u FROM Usuario u WHERE u.status = true AND u.email = :email")
    Usuario findByEmailAndStatusTrue(@Param("email") String email);

}
