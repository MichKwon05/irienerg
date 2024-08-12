package com.ineel.ifm.controller.Monitoreo;


import com.ineel.ifm.config.ApiResponse;
import com.ineel.ifm.controller.Monitoreo.dto.MonitoreoDto;
import com.ineel.ifm.service.monitoreo.MonitoreoService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/monitoreo")
@CrossOrigin(origins = {"*"})
@AllArgsConstructor

public class MonitoreoController {

    private final MonitoreoService monitoreoService;

    @PostMapping("/")
    public ResponseEntity<ApiResponse> register (@RequestBody MonitoreoDto monitoreoDto){
        return monitoreoService.save(monitoreoDto.toEntity());
    }


    @GetMapping("/")
    public ResponseEntity<ApiResponse> getAll(){
        return monitoreoService.getAll();
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse> update(@PathVariable Long id, @RequestBody MonitoreoDto monitoreoDto){
        return monitoreoService.update(id, monitoreoDto.toEntity());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse> delete(@PathVariable long id){
        return monitoreoService.delete(id);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse> get(@PathVariable long id){
        return monitoreoService.get(id);
    }

}
