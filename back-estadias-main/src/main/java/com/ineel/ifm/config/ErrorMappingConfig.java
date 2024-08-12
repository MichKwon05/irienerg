package com.ineel.ifm.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class ErrorMappingConfig {

    @Bean
    public Map<Integer, String> errorMapping() {
        Map<Integer, String> errorMap = new HashMap<>();
        errorMap.put(1, "Protección Isla");
        errorMap.put(2, "Sobrecorriente CA");
        errorMap.put(3, "Sobre Voltaje bus CD-HW");
        errorMap.put(4, "Voltaje CA bajo UV1");
        errorMap.put(5, "Voltaje CA bajo UV2");
        errorMap.put(6, "Voltaje CA alto OV1");
        errorMap.put(7, "Voltaje CA alto OV2");
        errorMap.put(8, "Frecuencia de red baja");
        errorMap.put(9, "Frecuencia de red alta");
        errorMap.put(10, "Temperatura alta");
        errorMap.put(11, "Variación de frecuencia de red");
        errorMap.put(21, "Voltaje CD bajo");
        errorMap.put(22, "Voltaje CD alto");
        errorMap.put(23, "Sobrecorriente CD");
        errorMap.put(24, "Sobrevoltaje Bus CD-SW");
        errorMap.put(25, "Inicializando DSP");
        errorMap.put(31, "Sin conexión a WI-FI");
        errorMap.put(32, "Error inicio RTC");
        errorMap.put(33, "Error SD CARD");
        errorMap.put(34, "Sin conexión a servidor");
        errorMap.put(40, "Normal");
        return errorMap;
    }
}