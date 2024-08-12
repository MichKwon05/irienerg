package com.ineel.ifm.model.data;

import com.ineel.ifm.model.data.Data;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface DataRepository extends JpaRepository<Data, Long> {
    List<Data> findByTypeAndDateBetween(String type, LocalDate startDate, LocalDate endDate);
}
