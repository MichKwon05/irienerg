package com.ineel.ifm.controller.Datos;

import com.ineel.ifm.config.ApiResponse;
import com.ineel.ifm.service.datos.DataService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.Map;

@RestController
@RequestMapping("/api/data")
@CrossOrigin(origins = {"*"})
@AllArgsConstructor
public class DataController {
    private final DataService dataService;

    @GetMapping("/{type}")
    public ResponseEntity<ApiResponse> getData(
            @PathVariable String type,
            @RequestParam Map<String, String> params
    ) {
        try {
            String startDateStr = params.get("startDate");
            String endDateStr = params.get("endDate");

            LocalDate startDate = LocalDate.parse(startDateStr);
            LocalDate endDate = LocalDate.parse(endDateStr);

            return dataService.getData(type, startDate, endDate);
        } catch (Exception e) {
            ApiResponse response = new ApiResponse(HttpStatus.BAD_REQUEST, true, "Invalid parameters");
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/")
    public ResponseEntity<ApiResponse> getAllData() {
        return dataService.getAllData();
    }
}
