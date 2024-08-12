package com.ineel.ifm.service.monitoreo;

import com.ineel.ifm.config.ApiResponse;
import com.ineel.ifm.model.monitoreo.Monitoreo;
import com.ineel.ifm.model.monitoreo.MonitoreoRepository;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
@AllArgsConstructor
@Transactional
public class MonitoreoService {

    private final MonitoreoRepository monitoreoRepository;
    private final Map<Integer, String> errorMapping;

    @Transactional(rollbackOn = Exception.class)
    public ResponseEntity<ApiResponse> save(Monitoreo monitoreo) {
        monitoreo.setErrorDescription(errorMapping.get(monitoreo.getErr()));
        Monitoreo savedMonitoreo = monitoreoRepository.save(monitoreo);
        return new ResponseEntity<>(new ApiResponse(savedMonitoreo, HttpStatus.CREATED), HttpStatus.CREATED);
    }

    @Transactional(rollbackOn = Exception.class)
    public ResponseEntity<ApiResponse> update(Long id, Monitoreo monitoreo) {
        Optional<Monitoreo> existingMonitoreo = monitoreoRepository.findById(monitoreo.getId());
        if (existingMonitoreo.isPresent()) {
            monitoreo.setErrorDescription(errorMapping.get(monitoreo.getErr()));
            Monitoreo updatedMonitoreo = monitoreoRepository.save(monitoreo);
            return new ResponseEntity<>(new ApiResponse(updatedMonitoreo, HttpStatus.OK), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(new ApiResponse(HttpStatus.NOT_FOUND, true, "Monitoreo not found"), HttpStatus.NOT_FOUND);
        }
    }

    @Transactional(rollbackOn = Exception.class)
    public ResponseEntity<ApiResponse> delete(Long id) {
        Optional<Monitoreo> existingMonitoreo = monitoreoRepository.findById(id);
        if (existingMonitoreo.isPresent()) {
            monitoreoRepository.deleteById(id);
            return new ResponseEntity<>(new ApiResponse(HttpStatus.OK, false, "Monitoreo deleted successfully"), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(new ApiResponse(HttpStatus.NOT_FOUND, true, "Monitoreo not found"), HttpStatus.NOT_FOUND);
        }
    }

    @Transactional(rollbackOn = Exception.class)
    public ResponseEntity<ApiResponse> get(Long id) {
        Optional<Monitoreo> existingMonitoreo = monitoreoRepository.findById(id);
        if (existingMonitoreo.isPresent()) {
            Monitoreo monitoreo = existingMonitoreo.get();
            monitoreo.setErrorDescription(errorMapping.get(monitoreo.getErr()));
            return new ResponseEntity<>(new ApiResponse(monitoreo, HttpStatus.OK), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(new ApiResponse(HttpStatus.NOT_FOUND, true, "Monitoreo not found"), HttpStatus.NOT_FOUND);
        }
    }

    @Transactional(rollbackOn = Exception.class)
    public ResponseEntity<ApiResponse> getAll() {
        List<Monitoreo> monitoreos = monitoreoRepository.findAll();
        monitoreos.forEach(monitoreo -> monitoreo.setErrorDescription(errorMapping.get(monitoreo.getErr())));
        return new ResponseEntity<>(new ApiResponse(monitoreos, HttpStatus.OK), HttpStatus.OK);
    }
}