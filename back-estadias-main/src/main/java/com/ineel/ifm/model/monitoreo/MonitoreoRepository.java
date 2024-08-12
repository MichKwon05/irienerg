package com.ineel.ifm.model.monitoreo;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MonitoreoRepository extends JpaRepository<Monitoreo,Long> {

    Optional<Monitoreo> findById(int id);

}
