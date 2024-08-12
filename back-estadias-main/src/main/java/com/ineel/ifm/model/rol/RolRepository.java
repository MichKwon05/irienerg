package com.ineel.ifm.model.rol;

import org.springframework.data.jpa.repository.JpaRepository;

public interface RolRepository extends JpaRepository<Rol,Long> {

    Rol getById(Long id);
    Rol findByName(String rol);
}
