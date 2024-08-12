package com.ineel.ifm.controller.Monitoreo.dto;


import com.ineel.ifm.model.monitoreo.Monitoreo;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@NoArgsConstructor
@Data
public class MonitoreoDto {

    private LocalDateTime event;
    private float vin;
    private float iin;
    private float potin;
    private float vbus;
    private float dcy;
    private float vout;
    private float iout;
    private float frec;
    private float tade;
    private float tinv1;
    private float poten;
    private float energy;
    private float frec1;
    private float frec2;
    private float frec3;
    private int err;
    private float eff;
    private float ene;
    private LocalDateTime fecha;
    private String errorDescription; // Agregado

    public Monitoreo toEntity(){
        Monitoreo monitoreo = new Monitoreo(event, vin, iin, potin, vbus, dcy, vout, iout, frec, tade, tinv1, poten, energy, frec1, frec2, frec3, err, eff, ene, fecha);
        monitoreo.setErrorDescription(errorDescription); // Set the error description
        return monitoreo;
    }
}
