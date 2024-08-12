package com.ineel.ifm.model.monitoreo;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Table(name = "monitoreo")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Monitoreo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "event")
    private LocalDateTime event;

    @Column(name = "Vin")
    private float vin;

    @Column(name = "Iin")
    private float iin;

    @Column(name = "Potin")
    private float potin;

    @Column(name = "Vbus")
    private float vbus;

    @Column(name = "Dcy")
    private float dcy;

    @Column(name = "Vout")
    private float vout;

    @Column(name = "Iout")
    private float iout;

    @Column(name = "Frec")
    private float frec;

    @Column(name = "Tade")
    private float tade;

    @Column(name = "Tinv1")
    private float tinv1;

    @Column(name = "Poten")
    private float poten;

    @Column(name = "Energy")
    private float energy;

    @Column(name = "Frec1")
    private float frec1;

    @Column(name = "Frec2")
    private float frec2;

    @Column(name = "Frec3")
    private float frec3;

    @Column(name = "Err")
    private int err;

    @Column(name = "Eff")
    private float eff;

    @Column(name = "Ene")
    private float ene;

    @Column(name = "fecha")
    private LocalDateTime fecha;

    @Transient // Este campo no se persiste en la base de datos
    private String errorDescription;

    public Monitoreo(LocalDateTime event, float vin, float iin, float potin, float vbus, float dcy, float vout, float iout, float frec, float tade, float tinv1, float poten, float energy, float frec1, float frec2, float frec3, int err, float eff, float ene, LocalDateTime fecha) {
        this.event = event;
        this.vin = vin;
        this.iin = iin;
        this.potin = potin;
        this.vbus = vbus;
        this.dcy = dcy;
        this.vout = vout;
        this.iout = iout;
        this.frec = frec;
        this.tade = tade;
        this.tinv1 = tinv1;
        this.poten = poten;
        this.energy = energy;
        this.frec1 = frec1;
        this.frec2 = frec2;
        this.frec3 = frec3;
        this.err = err;
        this.eff = eff;
        this.ene = ene;
        this.fecha = fecha;
    }
}
