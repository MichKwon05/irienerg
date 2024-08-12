package com.ineel.ifm.service.datos;

import com.ineel.ifm.config.ApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class DataService {

    public ResponseEntity<ApiResponse> getData(String type, LocalDate startDate, LocalDate endDate) {
        try {
            Object data = fetchData(type, startDate, endDate);
            ApiResponse response = new ApiResponse(data, HttpStatus.OK, false, "Data fetched successfully");
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
            ApiResponse response = new ApiResponse(HttpStatus.BAD_REQUEST, true, "Error fetching data");
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }
    }

    private Object fetchData(String type, LocalDate startDate, LocalDate endDate) {
        return new Object();
    }

    public ResponseEntity<ApiResponse> getAllData() {
        try {
            List<Object> data = fetchAllData();
            ApiResponse response = new ApiResponse(data, HttpStatus.OK, false, "All data fetched successfully");
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
            ApiResponse response = new ApiResponse(HttpStatus.BAD_REQUEST, true, "Error fetching all data");
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }
    }
    private List<Object> fetchAllData() {
        return List.of(new Object(), new Object());
    }
}
